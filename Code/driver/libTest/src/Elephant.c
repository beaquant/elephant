/*
 ============================================================================
 Name        : Elephant.c
 Author      : Eric
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <Jconfig.h>
#include <stdio.h>
#include "types.h"
#include "pump.h"

int  main()
{
	cJSON *root = NULL;
	char *tmp = NULL;
	configManager *manager = NULL;

	manager = initConfigManager();

	printf("config manager init ok \n");

	saveConfigFiles(manager);

	printf("save config manager ok \n");

	delConfigManager(manager);

	return 0;
}
