Role Based Access Control(RBAC)
	
RBAC is system which allows access to the user based on Roles. Roles consists of what resources user has access to and what action type are allowed for the roles.

=============================================================================================
Usage:
=============================================================================================

1. To use the project, instance of object RBACSystem should be created. Using the instance below methods could be used.
	a) addRoleToUser
	b) removeRoleFromUser
	c) hasPermission.
2. Code Snippets for usages of above exposed methods.
	i) Add Role For User
		public boolean AddRole() {
			System.out.println("Please enter User Email address");
			Scanner sc = new Scanner(System.in);
			String userMail = sc.nextLine();
			System.out.println("Please enter Role id to assign");
			String roleID = sc.nextLine();
			RBACSystem obj = new RBACSystem();
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(Constants.USER_EMAIL, userMail);
			options.put(Constants.ROLE_ID, roleID);
			boolean status = obj.addRoleToUser(options);
			return status;
		}

	ii) Remove Role for User
		public boolean RemoveRole() {
			System.out.println("Please enter User Email address");
			Scanner sc = new Scanner(System.in);
			String userMail = sc.nextLine();
			System.out.println("Please enter Role id to assign");
			String roleID = sc.nextLine();
			RBACSystem obj = new RBACSystem();
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(Constants.USER_EMAIL, userMail);
			options.put(Constants.ROLE_ID, roleID);
			boolean status = obj.removeRoleFromUser(options);
			return status;
		}

	iii) User has access to resource with action type
		public boolean whetherUserHasPermission() {
			System.out.println("Please enter User Email address");
			Scanner sc = new Scanner(System.in);
			String userMail = sc.nextLine();
			System.out.println("Please enter Resource id");
			String resourceID = sc.nextLine();
			System.out.println("Please enter action id");
			String actionID = sc.nextLine();
			RBACSystem obj = new RBACSystem();
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(Constants.USER_EMAIL, userMail);
			options.put(Constants.RESOURCE_ID, resourceID);
			options.put(Constants.ACTION_TYPE, actionID);
			boolean status = obj.hasPermission(options);
			return status;
		}

=============================================================================================
Implementation Details :
=============================================================================================
1. Project has been implemented with 6 entities:
	a) Users
		i)   Employee ID
		ii)  User Name
		iii) Address
		iv)  EmailID
		v)   Phone Number
	b) Roles
		i)   Role ID
		ii)  Role Name
		iii) Description
	c) Resources
		i)   ID
		ii)  Resource Type
		iii) Resource Name
		iv)  Resource Details 
	d) ActionType
		i)   ID
		ii)  Action Name
	e) Assignments - Relationship between Users and Roles Entity. User can have multiple roles and roles can have multiple user. Composite Key of this entity is both the columns. User and roles has many to many relationship.
		i)   User Email
		ii)  Role ID
	f) Permissions - Relationship between Roles, Resource and ActionType. Roles can have multiple resources and resources can belong to multiple roles. Roles and Resources has many to many relationship.
		i)   Role ID
		ii)  Resource ID
		iii) ActionID

2. Project implements three APIs as asked namely:
	a) public boolean addRoleToUser(Map<String, Object> options);
		i)   options contains userEmail and RoleID for this method. 
		ii)  Method implementation first checks the presence of userEmail and roleID. If not found then return false
		iii) Then it checks for userEmail in Users entity and RoleID in Role Entity. If not found then return false and logs appropiate messsage.
		iv)  It also checks whether user has been already assigned this role. If yes then return false with log saying user already has this roles.
		v) Adds entry to Assignment table.

	b) public boolean removeRoleFromUser(Map<String, Object> options);
		i)   options contains userEmail and RoleID for this method. 
		ii)  Method implementation first checks the presence of userEmail and roleID. If not found then return false
		iii) Then it checks for userEmail in Users entity and RoleID in Role Entity. If not found then return false and logs appropiate messsage.
		iv)  It also checks whether user and role record exist in db or not. If no then return false with log saying user doesn;t have this role.
		v) Removes entry to Assignment table.
	c) public boolean hasPermission(Map<String, Object> options);
		i) options contains userEmail, actionID and ResourceID for this method.
		ii)  Method implementation first checks the presence of all the options used in i). If not found then return false.
		iii) Then it checks for presence of record in appropiate entity. If not found then return false and logs appropiate messsage.
		iv) Find all the roles which user is assigned.
		v)  Iterate through all the roles to check for resource and Action pairs. If found then return true as user has access 


3. Facade design pattern is used to hide interations between the interfaces. Facade layer is IRBACSystem interface which exposes the APIs to use.
4. SampleData class under package com.org.assignment.GenerateData is to generate in memory data.
5. Database handler has been implemented under the package com.org.assignment.Database. 
6. Factory Pattern has been used to implement database handle. Currently it uses InMemoryDB class 	 which actually interacts with in memory data following the database class terminologies. As it is loosely coupled, it can be easily replaced with Actual Database.
7. Entities are defined under package com.org.assignment.RBAC.Entities. 
8. Utils classes has been defined in com.org.assignment.Utils.
9. Layering has been following appropiately.
10.Integaration test has been written to test the application functionality for the API exposed.
11. Project also uses Singleton Pattern for creating database instance.

=============================================================================================
Following Sample data has been hardcoded with the application to test the application.
=============================================================================================

Users

1  iiit.rakesh6288@gmail.com   Rakesh   Hyderabad   9949231286
2  anish.g@gmail.com           Anish    Bangalore   9949231456
3  aman.x@gmail.com            Aman     Pune        9945631456

Roles

1   Admin                       Admin has access to all the resources
2   Software Developer Team 1   Developer of Team 1 needs access team specific resources
3   Software Developer Team 2   Developer of Team 2 needs access team specific resources

Resources


1    Redis                  InMemoryResource
2    SQL Server 2008        Server
3    MemCache               InMemoryResource
4    Repository Team1       BitBucket
5    Repository Team2       BitBucket

ActionType

1   read
2   write
3   delete

Assignments

iiit.rakesh6288@mgmail.com   1
anish.g@gmail.com            2
aman.g@gmail.com             3

Permissions

RoleID    ResourceID    ActionType
  1           1 			1
  1           1 			2
  1 		  1 			3
  1 		  2 			1
  1 		  2 			2
  1           2 			3
  1 		  3 			1
  1 		  3 			2
  1 		  3			    3
  1 		  4 			1
  1 		  4 			2
  1           4 			3
  1 		  5 			1
  1           5 			2
  1 		  5 			3
  2 		  4 			1
  2 		  4 			2
  2 		  4 			3
  2 		  5 			1
  3 		  5 			1
  3 		  5 			2
  3 		  5 			3

=============================================================================================
Assumptions:
=============================================================================================
1. It has been implemented with InMemoryData.
2. User maps to Role in Assignment entity.
3. Role maps to resources in Permissions entity.
4. Factory Pattern has been implemented for Database. Currently it is pluged to inMemoryDB but can be replaced with actual DB.

=============================================================================================
Pending Implementations:
=============================================================================================

1. Roles can be child of Roles i.e creating new roles from roles.
2. Logger Implementation.
3. Exception handling.
4. Unit test cases.
5. Currently it is implemented with InMemory data structures. Needed to be tested with actual DB.
6. More improvement on SOLID Principles.
7. APIs for adding resources to roles.


