import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { BookService } from 'src/app/service/book.service';

@Component({
  selector: 'app-updatebookdetails',
  templateUrl: './updatebookdetails.component.html',
  styleUrls: ['./updatebookdetails.component.css'],
})
export class UpdatebookdetailsComponent {
  isbn: string = '';

  book: any;

  constructor(private bookService: BookService) { }

  ngOnInit(): void { }

  getByIsbn(): void {
    this.bookService.getByIsbn(this.isbn).subscribe(
      (response) => {
        this.book = response;
      },

      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;

        alert(errorMessage);
      }
    );
  }

  updateTitle(): void {
    this.bookService
      .updateBookTitleByISBN(this.isbn, this.book.title)
      .subscribe(
        (response) => {
          console.log('Title updated successfully');

          alert('Title updated successfully');
        },

        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;

          alert(errorMessage);
        }
      );
  }

  updateDescription(): void {
    this.bookService
      .updateBookDescriptionByISBN(this.isbn, this.book.description)
      .subscribe(
        (response) => {
          console.log('Description updated successfully');

          alert('Description updated successfully');
        },

        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;

          alert(errorMessage);
        }
      );
  }

  updateCategory(): void {
    this.bookService
      .updateBookCategoryByISBN(this.isbn, this.book.category)
      .subscribe(
        (response) => {
          console.log('Category updated successfully');

          alert('Category updated successfully');
        },

        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;

          alert(errorMessage);
        }
      );
  }

  updateEdition(): void {
    this.bookService
      .updateBookEditionByISBN(this.isbn, this.book.edition)
      .subscribe(
        (response) => {
          console.log('Edition updated successfully');

          alert('Edition updated successfully');
        },

        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;

          alert(errorMessage);
        }
      );
  }

  updatePublisherId(): void {
    this.bookService
      .updateBookPublisherByISBN(this.isbn, this.book.publisherId)
      .subscribe(
        (response) => {
          console.log('Publisher updated successfully');

          alert('Publisher updated successfully');
        },

        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;

          alert(errorMessage);
        }
      );
  }

  isIsbnValid(): boolean {
    return this.isbn !== '';
  }

  isTitleValid(): boolean {
    return this.book && this.book.title !== '';
  }

  isDescriptionValid(): boolean {
    return this.book && this.book.description !== '';
  }

  isCategoryValid(): boolean {
    return this.book && this.book.category !== '';
  }

  isEditionValid(): boolean {
    return this.book && this.book.edition !== '';
  }

  isPublisherIdValid(): boolean {
    return this.book && this.book.publisherId !== '';
  }
}
