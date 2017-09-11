// RemoteInterface.aidl
package com.example.serverclient;

// Declare any non-default types here with import statements

interface RemoteInterface {

   int getPid();
   /**
    * Demonstrates some basic types that you can use as parameters
    * and return values in AIDL.
    */
   String stuff();

}
