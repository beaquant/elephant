/*****************************************************************************
*
*	Copyright(c) 2017-2020 Author(Zhihao). As an unpublished work
*	All rights reserved.
*
*	Module Description:
*		This is a template file for source code file
*		(Fill in a detailed description of the module's function here)
*	$URL: 		$
*	$Author: 	$
*	$Revision:  $
*	$Date:  	$
*-----------------------------------------------------------------------------
*   The information contained herein is confidential property of Zhihao
*   The use, copying, transfer or disclosure of such information is prohibited
*   except by express written agreement with Zhihao.
*****************************************************************************/


/*****************************************************************************
*	Include Section
*	add all #include here
*****************************************************************************/
#include<stdarg.h>
#include<time.h>
#include<string.h>
#include<stdlib.h>
#include"logger.h"


/*****************************************************************************
* Define section
* add all #define here
*****************************************************************************/

/****************************************************************************
* ADT section
*	add definition of user defined Data Type that only be used in this file  here
***************************************************************************/


/******************************************************************************
* Function prototype section
* add prototypes for all functions called by this file,excepting those
* declared in header file
*****************************************************************************/


/*****************************************************************************
* Global variables section - Exported
* add declaration of global variables that will be exported here
* e.g.
*	int8_t foo;
****************************************************************************/

/*****************************************************************************
* Global variables section - Local
* define global variables(will be refered only in this file) here,
* static keyword should be used to limit scope of local variable to this file
* e.g.
*	static uint8_t ufoo;
*****************************************************************************/

/* function body */

/*****************************************************************************
* Description:
*		add funtion description here
* Parameters:
*		description for each argument, new argument starts at new line
* Return:
*		what does this function returned?
*****************************************************************************/

FILE * fp;
static int SESSION_TRACKER; //Keepstrackofsession

char * print_time(void)
{
	int size = 0;
	time_t t;
	char* buf;

	t = time(NULL);/*get current calendar time*/

	char*timestr = asctime(localtime(&t));
	timestr[strlen(timestr) - 1] = 0; //Gettingridof\n

	size = strlen(timestr) + 1 + 2; //Additional+2forsquarebraces
	buf = (char*) malloc(size);

	memset(buf, 0x0, size);
	snprintf(buf, size, "[%s]", timestr);

	return buf;
}
void log_print(char* filename, int line, char* fmt, ...)
{
	va_list list;
	char *p, *r;
	int e;

	if (SESSION_TRACKER > 0)
		fp = fopen("log.txt", "a+");
	else
		fp = fopen("log.txt", "w");

	fprintf(fp, "%s", print_time());
	fprintf(fp, "[%s][line:%d]", filename, line);
	va_start(list, fmt);

	for (p = fmt; *p; ++p) {
		if (*p != '%') { //If simple string
			fputc(*p, fp);
		} else {
			switch (*++p) {
				/*string*/
				case 's': {
					r = va_arg(list, char*);
					fprintf(fp, "%s", r);
					continue;
				}
				/*integer*/
				case 'd': {
					e = va_arg(list, int);
					fprintf(fp, "%d", e);
					continue;
				}
				default:
					fputc(*p, fp);
			}
		}
	}
	va_end(list);
	fputc('\n', fp);
	SESSION_TRACKER++;
	fclose(fp);
}

void debug_print(char* filename, int line)
{
	char s[250]={0,};

	sprintf(s, "%s", print_time());printf("%s, ",s);
	sprintf(s, "[%s][line:%d]", filename, line);
	printf("%s, ",s);
//	printf(fmt);

}
/********************************EndOfFile********************************/
