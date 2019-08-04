
# JEngine
JEngine spawned as a rewrite of a video game I wrote in high school. A few days into optimizing and reorganizing the code, I decided to create a full fledged 2D game engine. There are still a few bugs to iron out and areas I need to address but regardless, it is possible for anyone to make a video game utilizing this engine.

The following write up will be updated over the course of the next two months.
<br>
**Component Based Architecture:**</br>
The base class for all parent objects is a game object. Parents are parameterized as mentors. 
The base class for child objects is component. Children are parameterized as component.
Below is a code snippet for the actual implementation of the component based architecture.
![](https://i.imgur.com/0u7bTDk.png)

This architecture allows for both the parent (mentor) and child (component) to have direct access to one another and invoke their appropriate functions. 
<br>
**Dynamic Audio Scaling**
Dynamic Audio Scaling is a feature in this engine to allow creators to tag/categorize game sounds, music, etc. as lines or categories. Through these categories, creators may create a new line or category on the AudioMaster by calling the static function:

       AudioLine audioLine = new AudioLine("Your line name");
The following code shows how to set the volume of an audio line and how to adjust all game sounds.

	   audioLine.setVolLinear(float fraction); // Scales the volume based on a fraction or percentage.
	   audioLine.setVolDB(float db); //Sets the volume of the line to a specified DB.
	   audioLine.scaleVol(float fraction); //Scales the volume fractionally. 

To scale down all volumes simply call the following function.

	    AudioMaster.scaleVolume(float volFrac); //Scales all audio lines (and subsequent game sounds) based on the given fraction/percentage.

