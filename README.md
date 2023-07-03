# panache-delete

I have a delete method using the Criteria API.
In legacy code, with JPA implementation EclipseLink JPA, this method works correctly. 
But in a Quarkus 3.1.3 (with Hibernate 6.2.5) application I am getting an error.
I have two equivalent methods, one use Panache with HQL and another use Criteria API (  method write on vanilla Criteria JPA, this work in the Legacy)

I rewrote method for correct work, but I can't understand what wrong in realisation old method from the Legacy?
Why this doesn't work with new Hibernate?