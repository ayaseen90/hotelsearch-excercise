# hotelsearchexcercise
This Project provides a UI and logic to access Expedia Exercise RestAPI.
The project consists of a backend part (this project) and a front end part (hotelsearch-ui)

 <h1> Installation Instructions </h1>
 the project is managed by <b>Maven</b> and uses an embedded container.
 to setup an environment you need the following prerequisites:
 
 <ul>
 <li> JDK 1.8 or higher </li>
 <li> Maven </li>
 </ul>
 
 After downloading the maven project navigate to the directory of the project then run:
 mvn clean install jetty:run
 
 the above command will clean the environment, then builds and installs the project dependencies, then start jetty application server after feeding it with the generated WAR file
 
 
 Live Demo can be found at:
 https://hotelsearch-excercise.herokuapp.com/

This Web Application is deployed using an embedded Jetty container.
The project consists of four main packages:
control: contains Rest APIs which are used by the front-end project.
json: for parsing and deserializing the returned JSON from the webservice
 model: POJOs used to store information from the returned objects from expedia webservice
 search: manages the http clients and the objects related to search requests as well as the validation part
 
 
 
 
 Known Limitations:
 
 Search is only implemented to get Hotels,  Flights nor packages and activities are not implemented
 
 Although error handling is managed well on the server side, it is not being handled on client side as a production ready release
 
 The overall project was built using Java Technology as a backend side and Angular 4 as front end side.
 
 I have over five years of experience mostly in developing Java web based applications, I picked Java as it provides solid Object Oriented model which assists in building reusable components.
 
 For front-end I started learning Angular few days ago and thought it would be a good practice to develop this project using this technology.
 
 
 API Assumptions:
 
 After testing the API I found that there are:
 three main parameters that need to be present for the API to function:
 uid
 page
 scenario (couldn't find any other valid value other than dealfinder)
 
 The ProductType parameter presents the four values that can be returned by the API:
 1- Hotel 
 2- Flight
 3- Package (Flight + Hotel)
 4- LXActivities (requires the destinationName parameter)
 
 shared attributes in the above DealTypes are:
 OfferDateRange
 Destination
 Pricing Info
 