package com.expedia.excercise.hotelsearch.search.client;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.expedia.excercise.hotelsearch.search.ServiceException;

/**
 * The Search manager is the component responsible of communicating with the {@link SearchClient}
 * This is a singleton class which is responsible of creating the clients and controlling their number through a BlockingQueue
 * @author Anas
 *
 */
public class SearchManager {
	
	private static final Logger LOG = Logger.getLogger(SearchManager.class);
	

	private static volatile SearchManager instance;
	
	public static SearchManager getInstance() {
		if(instance == null) {
			synchronized (SearchManager.class) {
				if(instance == null) {
					instance = new SearchManager();
				}
			}
		}
		return instance;
	}
	

	/*
	 * Singleton implementation
	 * initializes the Search manager and creates the minimum number of clients
	 */
	private SearchManager()  {
		
		LOG.info("Initializing Search Manager");
		for(int i =0; i< minNumOfClients; i++) {
			addNewClientToQueue();
		}
	}

	/*
	 * The below variables control the number of clients to be created in the environment
	 * The min Number is used to preinitialize the clients and to add them to the pool
	 */
	private int minNumOfClients = 5;
	private int maxNumOfClients = 50;
	
	/*
	 * Time to wait for a client to become available
	 */
	private int minWaitTimeForClient = 2;
	private int maxWaitTimeForClient = 2;
	
	/*
	 * Tracker to the number of clients created
	 */
	private AtomicInteger numOfClients = new AtomicInteger(0);
	

	private void addNewClientToQueue() {
		try {
			queue.put(createClient());
			LOG.debug("Client Created and added to the pool");
			
		} catch (InterruptedException e) {
		}
	}
	
	private SearchClient createClient() {
		SearchClient client = new SearchClient();
		numOfClients.incrementAndGet();
		return client;
	}
	
	private BlockingQueue<SearchClient> queue = new LinkedBlockingQueue<>();
	
	
	private SearchClient retrieveClient() throws ServiceException {
		

		LOG.trace("Retrieving client from queue");
		SearchClient client = retrieveClientFromQueue();
		LOG.trace("Client removed from Queue");
		return client;
	}
	
	/**
	 * returns the client to the queue and increments the number of available clients created
	 * @param client
	 */
	private void returnClient(SearchClient client) {
		
		if(client == null) {
			return;
		}
		try {
			queue.put(client);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns an available {@link SearchClient}, if the queue us empty (all clients are being used), it retries polling from the queue
	 * if no client is available and the max number of clients is reached an exception is thrown
	 * @return
	 * @throws ServiceException
	 */
	private SearchClient retrieveClientFromQueue() throws ServiceException {
		SearchClient client = null;
		try {
			client = queue.poll(minWaitTimeForClient, TimeUnit.SECONDS);
		
		
			if(client == null) {
				if(maxWaitTimeForClient > 0 && numOfClients.get() >= maxNumOfClients) {
					client = queue.poll(maxWaitTimeForClient, TimeUnit.SECONDS);
				}
			}
		} catch (InterruptedException e) {
			//ignore
		}
		
		
		if(client == null && numOfClients.get() >= maxNumOfClients) {
			throw new ServiceException("max Num of Clients exceeded, consider adjusting the configuration");
		}
		
		
		return client;
	}

	void decreaseCounter() {
		numOfClients.decrementAndGet();
		LOG.info("Client terminated");
	}
	
	public String executeSearch(Map<String, String> parameters) throws ServiceException {
		SearchClient client = retrieveClient();
		String result = client.executeSearch(parameters);
		returnClient(client);
		return result;
	}
}
