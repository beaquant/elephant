/*
 ============================================================================
 Name        : Elephant.c
 Author      : Eric
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include "types.h"

#include "gpio.h"
#include "api.h"
//int  main()
//{
//	cJSON *root = NULL;
//	char *tmp = NULL;
//	configManager *manager = NULL;
//
//	manager = initConfigManager();
//
//	printf("config manager init ok \n");
//
//	saveConfigFiles(manager);
//
//	printf("save config manager ok \n");
//
//	delConfigManager(manager);
//
//	return 0;
//}

//void main(void)
//{
//	printf("firmware version: %s\n", getFirmwareVersion());
//}
/*
int main()
{
	struct json_object * aaa;
	aaa = json_object_from_file("/home/eric/github/elephant/Code/driver/libTest/config2.json");
	printf("%s\n",json_object_to_json_string(aaa));
	return 1;
}
*/
int main()
{
	printf("firmware version: %s\n", getFirmwareVersion());
	raspiGpioInit();

}

