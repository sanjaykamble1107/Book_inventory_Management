import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Publisher } from 'src/app/Models/publisher';
import { BookService } from 'src/app/service/book.service';
import { PublisherService } from 'src/app/service/publisher.service';

@Component({
  selector: 'app-getbypublisherid',
  templateUrl: './getbypublisherid.component.html',
  styleUrls: ['./getbypublisherid.component.css']
})
export class GetbypublisheridComponent implements OnInit{

  publisherId: string = '';

  publisherList: any = [];

  allPublisherList:Publisher[]=[];

  constructor(private bookService: BookService,private publisherService:PublisherService) { }
  ngOnInit(): void {
    
    this.publisherService.getAllPublisher().subscribe(
      (res: any) => {
        this.allPublisherList = res;
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }

  getBooksByPublisherId() {

    this.bookService.getBooksByPublisherId(this.publisherId).subscribe(
      (response) => {
        this.publisherList = response;
      },
      (error: HttpErrorResponse) => {

        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );
  }
}
