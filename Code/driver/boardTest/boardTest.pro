#-------------------------------------------------
#
# Project created by QtCreator 2017-03-24T06:24:16
#
#-------------------------------------------------

#QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = boardTest
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp

HEADERS  += mainwindow.h

FORMS    += mainwindow.ui

#LIBS     += -L/home/pi/github/elephant/Code/driver/clib/RASPI-DEBUG -lclib
LIBS     +=  -lclib

INCLUDEPATH += /home/pi/github/elephant/Code/driver/clib/src
DEPENDPATH += /home/pi/github/elephant/Code/driver/clib/src

DISTFILES +=

RESOURCES += \
    resoure.qrc
