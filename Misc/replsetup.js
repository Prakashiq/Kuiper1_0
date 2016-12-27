var replConfig={_id: "Kuiper", members: [{ _id: 0, host: 'localhost:30000', priority: 10},
	{ _id: 1, host: 'localhost:30001'},
	{ _id: 2, host: 'localhost:29999', arbiterOnly: true}]};

rs.initiate(replConfig);