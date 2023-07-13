import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/Models/book';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-getbytitle',
  templateUrl: './getbytitle.component.html',
  styleUrls: ['./getbytitle.component.css'],
})
export class GetbytitleComponent implements OnInit {
  title: string = '';
  titleList: any;
  bookList: Book[] = [];

  //book:Book=new Book();
  constructor(private bookService: BookService) {}

  ngOnInit(): void {
    this.bookService.getAllBooks().subscribe(
      (res:any) => {
        this.bookList = res;
        console.log(res);
      },
      (error: HttpErrorResponse) => {
        let errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }

  bookByTitle() {
    // const title = this.searchCriteria.title;

    this.bookService.getBooksByTitle(this.title).subscribe(
      (response: any) => {
        console.log(response);
        this.titleList = response;
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }
}
