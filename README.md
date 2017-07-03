easy-example-module
===========
[![Build Status](https://travis-ci.org/DANS-KNAW/easy-example-module.png?branch=master)](https://travis-ci.org/DANS-KNAW/easy-example-module)

<Remove this comment and extend the descriptions below>


SYNOPSIS
--------

    easy-example-module \
            <synopsis of command line parameters> \
            <...possibly continued again, or all joined on one line>


DESCRIPTION
-----------

An example module generated with easy-module-archetype.


ARGUMENTS
---------

    Options:

        --help      Show help message
        --version   Show version of this program

    Subcommand: run-service - Starts EASY Example Module as a daemon that services HTTP requests
        --help   Show help message
    ---

EXAMPLES
--------

    easy-example-module -o value


INSTALLATION AND CONFIGURATION
------------------------------


1. Unzip the tarball to a directory of your choice, typically `/usr/local/`
2. A new directory called easy-example-module-<version> will be created
3. Add the command script to your `PATH` environment variable by creating a symbolic link to it from a directory that is
   on the path, e.g. 
   
        ln -s /usr/local/easy-example-module-<version>/bin/easy-example-module /usr/bin



General configuration settings can be set in `cfg/application.properties` and logging can be configured
in `cfg/logback.xml`. The available settings are explained in comments in aforementioned files.


BUILDING FROM SOURCE
--------------------

Prerequisites:

* Java 8 or higher
* Maven 3.3.3 or higher

Steps:

        git clone https://github.com/DANS-KNAW/easy-example-module.git
        cd easy-example-module
        mvn install
