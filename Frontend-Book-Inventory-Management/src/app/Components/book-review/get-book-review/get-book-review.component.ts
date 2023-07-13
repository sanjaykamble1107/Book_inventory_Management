import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/Models/book';
import { BookReviewService } from 'src/app/service/book-review.service';
import { BookService } from 'src/app/service/book.service';
import { bookReview } from '../bookreview';

@Component({
  selector: 'app-get-book-review',
  templateUrl: './get-book-review.component.html',
  styleUrls: ['./get-book-review.component.css']
})
export class GetBookReviewComponent implements OnInit{
  isbn: string = '';
  bookReviews: bookReview[] = [];
  isGetReviewsClicked: boolean = false;
  bookList:Book[]=[];

  constructor(private bookReviewService: BookReviewService,private bookService:BookService) { }

  ngOnInit(): void {
    
    this.bookService.getAllBooks().subscribe(
      (res:any)=>{
        this.bookList=res;
      },(error:HttpErrorResponse)=>{
        let errorMessage=error.error.errorMessage;
        console.log(errorMessage);

      }
    );

  }


  

  getBookReviews() {
    this.isGetReviewsClicked = true;
    this.bookReviewService.getBookReviewsByISBN(this.isbn)
      .subscribe(
        response => {
          console.log('Book reviews retrieved successfully', response);
          this.bookReviews = response;
        },
        (error: HttpErrorResponse) => {

          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
        }
      );
  }
}
