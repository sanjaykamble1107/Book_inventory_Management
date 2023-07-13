import { Component } from '@angular/core';
import { Book } from 'src/app/Models/book';
import { BookService } from 'src/app/service/book.service';
import { AuthService } from 'src/app/Configuration/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  searchTitle: string | undefined;
  book!: Book;
  constructor(private bookService: BookService,private authService:AuthService) { }

isLoggedIn():boolean{
  return this.authService.isLoggedIn; 
}

logout(){
  this.authService.doLogout();
}

  searchBookByTitle() {}

  getBack(){
   let  roleNumber=this.authService.getRoleNumber();
    if(roleNumber!=null){
      this.authService.navigateToDashboard(roleNumber);
    }
    else
    this.authService.navigateToDashboard("home");
  }


}