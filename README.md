# Angular/Spring security auth service Demo
Demo app for register, login, logout with different user roles w/angular and spring security.

**Fugly as what, but this is for testing and sandbox.**

## About

Testing grounds for future reference. User can register to service, and depending on the role can check different content. 

The actual authentication is done with session with Spring Security. Tested in production at Heroku, and works. 

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

Java 11

## Development
- You can build continuesly to static file path with ng build --watch. Usefull if you are serving the content from root.

## Security
Basic authentication with Spring Security. The app checks for user principal, and shows content accordingly. The dialog with the browser is intercepted using angulars http interceptor.
- Role based auth, for secret role, and admin role: In-memory users first, and basic register functions then.

Some basic Angular guards added, mainly for redirecting on the client side.

## Todo

- Check for cors policy for fututer reference
- Junit Tests
- Validations

