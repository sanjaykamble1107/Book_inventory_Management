import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { BookConditionService } from 'src/app/service/book-condition.service';
import { Bookcondition } from 'src/app/Models/bookcondition';

@Component({
  selector: 'app-updatebookfulldescription',
  templateUrl: './updatebookfulldescription.component.html',
  styleUrls: ['./updatebookfulldescription.component.css']
})
export class UpdatebookfulldescriptionComponent {

  ranks: any = "";

  bookCondition =new Bookcondition();

  constructor(private bookService: BookConditionService) { }

  updateFullDescription() {
    console.log("updateFullDescription called");

    this.bookService.updateFullDescription(this.ranks, this.bookCondition).subscribe(
      (response) => {
        // Handle success response
        console.log('FullDescription updated successfully');
        alert('FullDescription updated successfully');
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }
}
