# Angular/Spring security auth service Demo
Demo app for register, login, logout with different user roles w/angular and spring security.

## About

Testing grounds for future reference. User can register to service, and depending on the role can check different content. 

The actual authentication is done with session with Spring Security. 

## Stack
Angular 13
Bootsrap 5
- Installation: npm install(i) bootsrap and npm install(i) @popperjs/core (in angular client folder)
- Place to styles: "./node_modules/bootstrap/dist/css/bootstrap.min.css"
- Place to scripts: "./node_modules/@popperjs/core/dist/umd/popper.min.js" and "./node_modules/bootstrap/dist/js/bootstrap.min.js"
- #### Note: The above does not work when building, there is a @character, and something else, that prevents build
    Ended up using links on index page
    
 Spring Boot 2.6.3
 - Spring Security

## Security
Basic authentication with Spring Security. The app checks for user principal, and shows content accordingly.

## Todo: 
- Role based auth, for secret role, and admin role: In-memory users first, and basic register functions then.
- Should check on cors policy, as it is not functioning atm. 
    - The project works fine from the root, when serving static content. Requires to build the angular client first, and then running spring.
