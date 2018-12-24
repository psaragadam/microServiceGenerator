# microServiceGenerator

MicroServiceGenerator application helps to generate micro service application automatically based on business details. 

We have to provide business details through front end application or json for for this API. we are getting zip file as project.

we can extract that file, we can just build and deploy into server. If you need you can modify application.


{
	"downloadDetails": {
		"jarDownload": true,
		"vcsDownload": true,
		"zipDownload": true
	},
	"integrationDetails": {
		"hasJPA": true,
		"hasJunit": true,
		"hasSwagger": true,
		"hasRestTemplate": true,
		"hasProfiling": true
	},
	"springProfile": "dev",
	"models": [
		{
			"jsonFormat": true,
			"modelName": "customer",
			"serialize": true,
			"entity": true,
			"fields": [
				{
					"fieldName": "name",
					"fieldType": "string",
					"required": true
				},
				{
					"fieldName": "phone",
					"fieldType": "string",
					"required": true
				},
				{
					"fieldName": "addresses",
					"fieldType": "list",
					"required": true,
					"havingRelation" : true,
					"relationType": "OneToMany",
					"joinColumn" : "uid"
				}
			]
		},
		{
			"jsonFormat": true,
			"modelName": "address",
			"serialize": true,
			"entity": true,
			"fields": [
				{
					"fieldName": "drno",
					"fieldType": "string",
					"required": true
				},
				{
					"fieldName": "street",
					"fieldType": "string",
					"required": true
				}
			]
		}
	],
	"projectDetails": {
		"buildToolName": "maven",
		"buildType": "jar",
		"packageName": "customer",
		"projectName": "customerApplicationRest"
	},
	"jpaProperties": {
		"url": "jdbc:******",
		"userName": "*****",
		"password": "******",
		"dialect": "org.hibernate.dialect.MySQL5InnoDBDialect",
		"ddl_Auto": "update"
	}
}
