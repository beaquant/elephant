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
#include "types.h"
#include "sysconfig.h"
#include "api.h"


/*****************************************************************************
* Define section
* add all #define here
*****************************************************************************/
#define INDEX_CHECK(idx)					do{\
																if(idx >= sysConfig.pump.number){\
																	return ERR_OUT_OF_RANGE;\
																}\
															}while(0)
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
*	uint8_t foo;
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

/*
 * 设置系统中泵的数量。
 * 参数: number为泵的数量。泵的数量需根据实际情况来设置，即用户在UI界面中输入。
 * 返回值: 0表示ok，其它参见error code。
*/
uint8_t pumperSetNumber(uint8_t number)
{
	if(number > MAX_PUMP_NUMBER){
		return ERR_OUT_OF_RANGE;
	}
	sysConfig.pump.number = number;
	return NO_ERR;
}


/*
 * 获取系统中泵的数量。
 * 返回值:泵的数量。
*/
uint8_t pumperGetNumber(void)
{
	return sysConfig.pump.number;
}


/*
 * 复位泵函数。将泵的注射器活塞移动到顶端0位，泵的阀旋转到位置0位。并且所有的命令参数初始化为默认值。
 * 参数idx: 泵序号
 * 返回值: 0表示ok，其它参见error code。
*/
uint8_t pumperReset(uint8_t idx)
{
	INDEX_CHECK(idx);
}

/*
 * 复位注射器函数。将泵的注射器活塞移动到顶端0位。
 * 参数idx: 泵序号
 * 返回值: 0表示ok，其它参见error code。
 */
uint8_t pumperInjectReset (uint8_t idx)
{
	INDEX_CHECK(idx);
}


/*
 * 复位阀门函数。泵的阀旋转到位置0位。
 * 参数idx: 泵序号
 * 返回值: 0表示ok，其它参见error code。
*/
uint8_t pumperValveReset (uint8_t idx)
{
	INDEX_CHECK(idx);
}



/*
 * 设置注射器容量。
 * 参数idx: 泵序号
 * 参数:ul 为注射器容量。单位微升(uL), 范围0-L。
 * 返回值: 0表示ok，其它参见error code。
 */
uint8_t pumperSetInjectCapacity (uint8_t idx, uint16_t ul)
{
	INDEX_CHECK(idx);
	sysConfig.pump.pumpData[idx].conf.injectCap = ul;
	return NO_ERR;
}


/*
 * 获取当前注射器容量。
 * 参数idx: 泵序号
 * 返回值:注射器容量。单位微升(uL), 范围0-L。
*/
uint16_t pumperGetInjectCapacity (uint8_t idx)
{
	INDEX_CHECK(idx);
	return sysConfig.pump.pumpData[idx].conf.injectCap;
}



/*
 * 注射器注入函数。注入多少微升液体。
 * 参数idx: 泵序号
 * 参数:ul 为注射器容量。单位微升(uL), 范围0-L。与初始化时输入的容量相关。
 * 返回值: 0表示ok，其它参见error code。
*/
uint8_t pumperInject(uint8_t idx, uint16_t ul)
{
	INDEX_CHECK(idx);

}



/*
 * 注射器剩余液体有多少。
 * 参数idx: 泵序号
 * 返回值: 注射器剩余容量。单位微升(uL), 范围0-L。与初始化时输入的容量相关。
 * 存储在本地文件中记录
*/
uint16_t pumperInjectRemain(uint8_t idx)
{
	INDEX_CHECK(idx);
	return sysConfig.pump.pumpData[idx].injectRemainCap;
}



/*
 * 注射器维护/更换时，使用这个函数。
 * 参数idx: 泵序号
 * */
uint8_t pumperInjectorRepair(uint8_t idx)
{

}




/* 升降台 */
/*
 * 复位升降台函数。将升降台移动到初始位置。
 * 返回值: 0表示ok，其它参见error code。
*/
uint8_t liftReset (void)
{

}



/*
 * 升降台向上移动函数。
 * 参数：mm，移动毫米。
 * 返回值: 0表示ok，其它参见error code。
*/
uint8_t liftMoveUp(uint16_t mm)
{

}


/*
 * 升降台向下移动函数。
 * 参数：mm，移动毫米。
 * 返回值: 0表示ok，其它参见error code。
*/
uint8_t liftMoveDown(uint16_t mm)
{

}


/*
 * 获取当前升降台相对位置。
 * 返回值: 当前升降台的相对于原点位置，单位毫米。
*/
uint16_t liftGetPosition(void)
{

}



/* 旋转台 */

/*
 * 停止旋转台旋转函数。
 * 返回值: 0表示ok，其它参见error code。
*/
uint8_t turnTableStop(void)
{

}



/*
 * 旋转台以指定的转速旋转函数。
 * 参数: rpm，转速，每分钟多少转。
 * 返回值: 0表示ok，其它参见error code。
*/
uint8_t turnTableStart(uint16_t rpm)
{

}



/********************************End Of File********************************/
