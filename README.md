# TodoAppAPI

Overview- TODO REST API caters the below - Add items to a list, Mark an item as Completed (handled with "Done" flag), Ability to delete items, Ability to restore items, Support for multiple lists, Ability to tag items within a list, Ability to add reminders to items.

Framework/Tool details -

Spring Boot 2.4, JDK  1.8 , Junit 5, JPA, H2 database.

H2 Console Link - http://localhost:port/h2_console

Sammple Input Request

Add Items { "requests":[ { "itemDescription" : "Shopping", "tagName" : "Shop", "reminder" : "30/04/2021 10:30:00" },{ "itemDescription" : "Studying", "tagName" : "Study", "reminder" : "30/04/2021 10:30:00" }

]
}

Update/Delete Item

{ "requests":[

	{"id" : "1",
	"itemDescription" : "Studying New",
	"tagName" : "Study",
	"reminder" : "31/04/2021 10:30:00",
	"done" : 1
	}
	
]
}

