# CSCI 440 - Database Systems

This is the base upstream repository for CSCI 440.  It contains homeworks as well as the class project 
information.

## Getting Your Private Copy (Clone)

Rather than the traditional Forking model, we are going to use a Copy model for the class.

Please use the following steps to create a *private* version of this repo for your work:

- Create a *private* repository in your own account by
    - Going to <https://github.com/new>
    - Enter the name `csci-440-fall2020-private`
    - Select `Private`
    - Navigate to the `Settings` -> `Manage Access` section
    - Add `1cg` as a collaborator
- Now run the following git commands, substituting your Github user name and NetID where required:
```bash
$ git clone https://github.com/msu/csci-440-fall2020.git csci-440-fall2020-private
$ cd csci-440-fall2020-private
$ git remote set-url origin git@github.com:<YOUR GITHUB USER NAME>/csci-440-fall2020-private.git
$ git remote add upstream https://github.com/msu/csci-440-fall2020.git
```

Whew!  You now have a private copy of the repository on github.  You can push and pull to this repository and branch with the standard `git pull` and `git push` commands.

When you want to get an update from the public class repository you can run this command:
```
$ git pull upstream master
```

## Getting Your Public Copy & Registering (Fork)

Next, please fork this repository to your personal account. You will now have two copies of the project, one public and one private.

The private repository will be used for your work and our grading.

The public repository can be used for contibuting back to the class project. DO NOT PUSH WORK CODE TO THIS REPOSITORY

I highly recommend against cloning the public respoitory to your local system, to avoid confusion between the two. You can edit files in the public repository via the web interface, and that will be much safer.

Please add a file to `/repos` named

`<YOUR NET ID>.txt`

with the git URL of your *private* repository, e.g.

`git@github.com:1cg/csci-440-fall2020-private.git`

And then create a pull request against this repository.

We will accept the pull request, and your private work repo will be registered for the class at that point.

## Homeworks

Each homework has it's own directory (`/hwk0`, `/hwk1`, etc.).  Please do your work in the homework directory

## Project: Chinook Web App

You will be implementing a web application against a SQL store, found in `/db/chinook.db`

The web application will be an open-ended project, but must include the following:

* All entities in the chinookdb must be surfaced in the web application
* The Employee entity must have all CRUD operations surfaced
* A tree UI of the Employee hierarchy
* Track search with at least two inputs must be implemented
* At least one report-like page must exist that makes use of a `GROUP BY` clause
* Implement at least one Redis cache

### Project Rubric

The project will be largely graded via automated tests.  The grading will be broken down along the following lines:

* 70% - The automated test suite (if the test suite passes, you are guaranteed to get a C on the project)
* 15% - A recorded demo, demonstrating the following functionality
  * Navigating the core entities in the database
  * CRUD operations on the Employee entity
  * Track search
  * Paging implemented in the Track main view 
  * A Group By based report page
* 10% - Code quality and satisfaction of the Redis cache, determined by manual inspection
* 5% - At least one use of htmx (https://htmx.org) in the application to add dynamic AJAX behavior

### Project Resources

* [Spark Java Docs](http://sparkjava.com/documentation)
* [Velocity Templates Docs](https://velocity.apache.org/engine/2.2/user-guide.html#loops)
* [Active JDBC Docs](https://javalite.io/activejdbc)
