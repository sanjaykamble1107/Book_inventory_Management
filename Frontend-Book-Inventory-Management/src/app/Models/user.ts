export class User {
    userId: number;
    lastName: String;
    firstName: String
    phoneNumber: number
    userName: String;
    password: String;
    roleNumber: number;

    constructor(){
        this.userId=0;
        this.lastName="";
        this.firstName="";
        this.phoneNumber=0;
        this.userName="";
        this.password="";
        this.roleNumber=1;
    }
}
