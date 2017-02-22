/*****************************************************************************
*
*	Copyright(c) 2009-2019 Author(Zhihao). As an unpublished work
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

#ifndef _TYPES_H_
#define _TYPES_H_
#ifdef __cplusplus
extern "C" {
#endif


/****************************************************************************
* #include section
*	add #include here if any
***************************************************************************/

/****************************************************************************
* #define section
*	add constant #define here if any
***************************************************************************/

#define	bitset(var, bitno)		((var) |= (0b00000001 << (bitno)))
#define bitclr(var, bitno)		((var) &= ~(0b00000001 << (bitno)))
#define bitget(var, bitno)		((var >> bitno) & 0b00000001) 
 /****************************************************************************
  *  section
  *	add function prototype here if any
  ***************************************************************************/


#ifndef	FALSE
	#define FALSE	0
#endif

#ifndef	TRUE
	#define TRUE	1	
#endif

#ifndef NULL
	#define NULL                   ((void *) 0)
#endif

#ifndef FAILURE
	#define FAILURE	0       /* read eeprom success*/
#endif

#ifndef SUCCESS
	#define	SUCCESS	1       /* read eeprom success*/
#endif


#ifndef ENABLE
	#define	ENABLE	                                (1)
#endif
#ifndef DISABLE
	#define DISABLE									(0)
#endif
/****************************************************************************
* ADT section
*	add Abstract Data Type definition here
***************************************************************************/
typedef signed char				    int8_t;
typedef unsigned char 			    uint8_t;
typedef signed int				    int16_t;
typedef unsigned int 			    uint16_t;
typedef signed long	int			    int32_t;
typedef unsigned long int			uint32_t;
typedef float               	    FLOAT;
typedef double              	    DOUBLE;

// typedef code                  	    CONST_T;
/****************************************************************************
*  extern variable declaration section
***************************************************************************/


/****************************************************************************
*  section
*	add function prototype here if any
***************************************************************************/




#ifdef __cplusplus
}
#endif
#endif


/********************************End Of File********************************/

