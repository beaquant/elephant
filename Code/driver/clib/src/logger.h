/*****************************************************************************
*
*	Copyright(c) 2017-2020 Author(Zhihao). As an unpublished work
*	All rights reserved.
*
*	Description:
*		This is a template file for header file
*		(Fill in a detailed description of the module's function here)
*
*	$URL: 		$
*	$Author: 	$
*	$Revision:  $
*	$Date:  	$
*
*-----------------------------------------------------------------------------
*   The information contained herein is confidential property of Zhihao
*   The use, copying, transfer or disclosure of such information is prohibited 
*   except by express written agreement with Zhihao.
*****************************************************************************/

#ifndef _LOGGER_H_
#define _LOGGER_H_

#ifdef __cplusplus
extern "C" {
#endif

/****************************************************************************
* #include section
*	add #include here if any
***************************************************************************/
#include <stdio.h>

/****************************************************************************
* #define section
*	add constant #define here if any
***************************************************************************/


/****************************************************************************
* ADT section
*	add Abstract Data Type definition here
***************************************************************************/


/****************************************************************************
*  extern variable declaration section
***************************************************************************/


/****************************************************************************
*  section
*	add function prototype here if any
***************************************************************************/

void log_print(char* filename, int line, char *fmt,...);
void debug_print(char* filename, int line);

//#define LOG_PRINT(...) log_print(__FILE__, __LINE__, __VA_ARGS__ )
#define LOG_PRINT(...)	debug_print(__FILE__,  __LINE__); printf(__VA_ARGS__ );printf("\n");

#ifdef __cplusplus
}
#endif

#endif


/********************************End Of File********************************/




