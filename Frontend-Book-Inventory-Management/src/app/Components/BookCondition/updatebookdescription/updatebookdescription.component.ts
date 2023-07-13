import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { BookConditionService } from 'src/app/service/book-condition.service';

@Component({
  selector: 'app-updatebookdescription',
  templateUrl: './updatebookdescription.component.html',
  styleUrls: ['./updatebookdescription.component.css']
})
export class UpdatebookdescriptionComponent {
  ranks!: any;

  description: any = {};

  constructor(private bookService: BookConditionService) { }

  updateDescription() {

    this.bookService.updateDescription(this.ranks, this.description).subscribe(
      response => {
        // Handle success response
        console.log('Description updated successfully');
        alert("Description updated successfully");
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }
}
