#-------------------------------------------------
#
# Project created by QtCreator 2017-03-24T06:24:16
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = boardTest
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp

HEADERS  += mainwindow.h

FORMS    += mainwindow.ui

LIBS     += -L/home/eric/github/elephant/Code/driver/clib/Debug -lclib

INCLUDEPATH += /home/eric/github/elephant/Code/driver/clib/src
DEPENDPATH += /home/eric/github/elephant/Code/driver/clib/src
