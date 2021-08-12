# Library_management
Library management system using rest api

Models:
1.Loan
  int loanId;
  Borrower borrower(relationship with borrower)
  LocalDateTime takenOut;
  LocalDateTime dueBack;
  int totalCopieslended;
  boolean isOverdue;
  titleID(relationship with title)
  int LoanReturnId;
  
2. Tittle
   int tittleId;
	 String tittleName;
	 String tittleType;
	 boolean isAvailable;
	 int totalcopies;
	 Loan_ID(relationship with Loan)
   
3.Borrower
  int borrowerId;
	tring borrowerName;
  Loan_id(relationship with loan)
  
  Endpoints:
  1./avaliableTitles method=get
	@ApiOperation(value = "Get All avaliable Inventory", notes = "returns all tiles details")
	
  2. /findOverdueTitles method=Get
    @ApiOperation(value = "Get All overdue loans", notes = "returns all overdue loan details")
    CRON JOB is schedules for every 1 min
	
  3./lendTitles method=POSt
	@ApiOperation(value = "lends item on loans to borrrower", notes = "returns  loan details")
	
  4./returnItem/{loanId} method=POST
	@ApiOperation(value = "return back lended tiles to library ", notes = "returns  nothing")
	
	5. /registerBorrower  method= POST
  @ApiOperation(value = "register the borrower ", notes = "returns  borrower details")
	
  6./addTittleItem    method=POST
	@ApiOperation(value = "adds tittles to library inventory ", notes = "returns added tittle  details")

	7./getTittles/{borrowerID} method=GET
	@ApiOperation(value = "borrowed titles for a user ", notes = "returns  tittle  details")
	
