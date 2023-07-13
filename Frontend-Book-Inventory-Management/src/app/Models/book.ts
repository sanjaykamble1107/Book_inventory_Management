export class Book {
    
    isbn!:string;
    category!: any;
    title!: string;
    description!: String;
    edition!: number;
    publisherId!: any;
constructor(){
    this.isbn= "";
    this.category=0;
    this.title="";
    this.description="";
    this.edition=0;
    this.publisherId=0;
}
}
