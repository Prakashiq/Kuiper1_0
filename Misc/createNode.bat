

	start /d "mongod --port 30001 --replSet "Kuiper" --dbpath c:\Kuiber\MongoDB\db_30001"
	
	start  /d "mongod --port 29999 --replSet "Kuiper" --dbpath c:\Kuiber\MongoDB\db_29999"
