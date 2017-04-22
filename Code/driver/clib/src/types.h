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
# include <stdint.h>


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

