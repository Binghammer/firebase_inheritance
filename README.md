Bug with inheritance in new Firebase sdk
---

See this [Stackoverflow question](http://stackoverflow.com/questions/37957693/firebase-database-object-inheritance-not-supported) reqarding this.

I created this to demonstrate that traditional object inheritance is not supported in the new [Firebase SDK](https://firebase.google.com/docs/android/setup).

In my opinion, supporting object inheritance is critical. Upgrading from the older firebase to the new setup has really been a struggle because of this regression. 

**Note:** 
To run this sample you will need: 
* to add a valid `google_services.json`
* Java8
* Latest Android SDK and Android Studio
