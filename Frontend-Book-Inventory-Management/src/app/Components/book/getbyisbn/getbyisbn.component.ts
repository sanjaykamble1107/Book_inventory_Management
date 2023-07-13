import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/Models/book';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-getbyisbn',
  templateUrl: './getbyisbn.component.html',
  styleUrls: ['./getbyisbn.component.css'],
})
export class GetbyisbnComponent implements OnInit {
  searchCriteria: any = {
    isbn: '',
  };
  booksByIsbnList: Book[] = [];
  book: Book = new Book();

  constructor(private bookService: BookService) {}

  ngOnInit(): void {}

  getByIsbn() {
    const isbn = this.searchCriteria.isbn;
    this.bookService.getByIsbn(isbn).subscribe(
      (response: any) => {
        this.book = response;
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }
}
