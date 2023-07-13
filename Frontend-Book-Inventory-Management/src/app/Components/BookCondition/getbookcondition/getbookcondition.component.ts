import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { BookConditionService } from 'src/app/service/book-condition.service';

@Component({
  selector: 'app-getbookcondition',
  templateUrl: './getbookcondition.component.html',
  styleUrls: ['./getbookcondition.component.css']
})
export class GetbookconditionComponent {
  ranks: string = '';
  bookCondition: any;

  constructor(private bookConditionService: BookConditionService) { }

  getBookCondition() {
    this.bookConditionService.getBookConditionByRanks(this.ranks)
      .subscribe((response: any) => {
        this.bookCondition = response;
      }, (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      });
  }
}
