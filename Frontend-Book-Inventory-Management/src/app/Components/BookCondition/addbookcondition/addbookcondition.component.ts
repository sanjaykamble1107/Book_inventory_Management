import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Bookcondition } from 'src/app/Models/bookcondition';
import { BookConditionService } from 'src/app/service/book-condition.service';

@Component({
  selector: 'app-addbookcondition',
  templateUrl: './addbookcondition.component.html',
  styleUrls: ['./addbookcondition.component.css']
})
export class AddbookconditionComponent {
  book: Bookcondition = new Bookcondition();

  constructor(private bookService: BookConditionService) { }

  saveBook(): void {
    this.bookService.addBookCondition(this.book).subscribe(
      (response: Object) => {
        // Handle successful response
        console.log('Book saved:', response);
        // Reset form or navigate to another page
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }
}
