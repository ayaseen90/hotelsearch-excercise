# hotelsearchexcercise
This Project provides a UI and logic to access Expedia Exercise RestAPI.
The project consists of a backend part (this project) and a front end part (hotelsearch-ui)

This Web Application is deployed using an embedded jetty container.

The project consists of four main packages:
control: contains Rest APIs which are used by the front-end project.
json: for parsing and deserializing the returned json from the webservice
 model: POJOs used to store information from the returned objects from expedia webservice
 search: manages the http clients and the objects related to search requests as well as the validation part
 
 the project is managed by <b>Maven</b>