import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-allbook',
  templateUrl: './allbook.component.html',
  styleUrls: ['./allbook.component.css']
})
export class AllbookComponent implements OnInit {
  books: any;

  constructor(private bookService: BookService) {}

  ngOnInit(): void {
    // Fetch all books on component initialization
    this.bookService.getAllBooks().subscribe(
      (res: any) => {
        // Assign the response to the books array
        this.books = [...res];
        console.log(this.books);
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }
}
