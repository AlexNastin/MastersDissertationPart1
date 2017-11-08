var target_db = db.getMongo().getDB("cars_ways");
if (target_db == "cars_ways") {
    if (target_db.getUser("master") == null) {
        target_db.createUser({
            user: "master",
            pwd: "master123",
            roles: [{role: "dbOwner", db: "cars_ways"}]
        });
        print('User created.')
    } else {
        print('User already exists.')
    }
}