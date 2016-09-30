# Pre-work - *DO IT!*

**DO IT!** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item, and adding notes to a specific task.

Submitted by: **Javier Arboleda**

Time spent: about **20** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **successfully add and remove items** from the todo list
* [x] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [x] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [x] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [x] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [x] **PARTIIALLY DONE** (not displayed in main todo list) - Add support for completion due dates for todo items (and display within listview item)
* [ ] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [ ] Add support for selecting the priority of each todo item (and display in listview item)
* [x] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [x] Add note feature allowing creation of note cards for each task

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

### _Start dialog message & add 'quick todo task':_

![screenshot](http://i.imgur.com/vOPQN3i.gif)

### _Open edit window, edit task, and pick a due date:_

![screenshot](http://i.imgur.com/KK1uYeA.gif)

### _Add notes & delete todo tasks:_

![screenshot](http://i.imgur.com/rxF9BGe.gif)

### _Long press add button for adding todo task with details:_

![screenshot](http://i.imgur.com/RXMTLiU.gif)

GIFs created with [AZ Screen Recorder](https://play.google.com/store/apps/details?id=com.hecorat.screenrecorder.free&hl=en).

## Notes

I wanted to use a new library, so decided to give Realm a shot. It was a bit challenging acclimating myself to it, but I am very happy that I used it and now know how to use it. It definitely is much cleaner and easier than using ContentProvider, SQLite, etc.

I was also challenged by trying to fill the page with the minimal info for todo tasks and keep the design aesthetically pleasing (e.g., not too much white space). 

With more time I would like to add more categorizing features to the main list (like group by date, sorting features, etc). I would also like to try out Firebase, and allow for user login and data persistence through cloud. I would also like to improve on the design and add some icons, and also add transitions/animations.

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
