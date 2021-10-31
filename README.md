# Groovy-TDD

## Application Description

Groovy is an App Fetching music PlayLists from a mocked api demonstrating Test Driven Development following London School



## How to Run
-use mockoon to mock an api response(Get Request return a fake playLists and there metadata into separate json responses)
playLists json : [
  {
    "id": "1",
    "name": "Hard Rock Cafe",
    "category": "rock"
  },
    {
    "id": "2",
    "name": "Chilled House",
    "category": "house"
  },
    {
    "id": "3",
    "name": "US TOP 40 HITS",
    "category": "mixed"
  },
    {
    "id": "4",
    "name": "90's Rock",
    "category": "rock"
  },
    {
    "id": "5",
    "name": "Purple Jazz",
    "category": "jazz"
  },
  {
    "id": "6",
    "name": "90's flashback",
    "category": "pop"
  },
  {
    "id": "7",
    "name": "Machine Funk",
    "category": "electro"
  },
  {
    "id": "8",
    "name": "Let's Groove",
    "category": "mixed"
  },
  {
    "id": "9",
    "name": "Feel The Beat",
    "category": "electro"
  },
  {
    "id": "10",
    "name": "Best Songs 2020",
    "category": "mixed"
  }
 ]
- playListDetails : {
  "id": "1",
  "name": "Hard Rock Cafe",
  "details": "Rock your senses with this timeless signature vibe list. \n\n • Poison \n • You shook me all night \n • Zombie \n • Rock'n Me \n • Thunderstruck \n • I Hate Myself for Loving you \n • Crazy \n • Knockin' on Heavens Door"
}

- install the app and run using the emulator it should load the playLists lists
- clicking the first item should navigate you to the second screen showing its details
- clicking any item other than the first one will result a snack bar with an error message
- run all the unit tests using the test suite provided in the test source set to confirm functionality
- run all acceptance tests in the android tests source set to confirm functionality


## Application Structure

-app consists of 2 fragments 1- get the playLists from the api using retrofit , flow, liveData

                            2- get the playListDetails  from the api using retrofit ,flow,livedata,navArgs
                           
                            
  -Skills Used: Test Driven Development ,dagger-hilt,navigation components,coroutines+flows,Retrofit,MVVM Pattern


## App Views
 - PlayLists Screen
 - PlayListDetails Screen


