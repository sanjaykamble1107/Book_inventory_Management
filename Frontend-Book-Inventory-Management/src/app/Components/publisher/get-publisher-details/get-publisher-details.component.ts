import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { PublisherService } from 'src/app/service/publisher.service';

@Component({
  selector: 'app-get-publisher-details',
  templateUrl: './get-publisher-details.component.html',
  styleUrls: ['./get-publisher-details.component.css']
})
export class GetPublisherDetailsComponent {
  publisherId!: number;
  name!: string;
  city!: string;
  stateCode!: string;
  publisher: any;

  constructor(private publisherService: PublisherService) { }

  getPublisherDetails() {
    if (this.publisherId) {
      this.publisherService.getPublisherById(this.publisherId)
        .subscribe((publisher: any) => {
          this.publisher = publisher;
        }, (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);

        });
    } else if (this.name) {
      this.publisherService.getPublisherByName(this.name)
        .subscribe((publisher: any) => {
          this.publisher = publisher;
        }, (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);

        });
    } else if (this.city) {
      this.publisherService.getPublisherByCity(this.city)
        .subscribe((publisher: any) => {
          this.publisher = publisher;
        }, (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);

        });
    } else if (this.stateCode) {
      this.publisherService.getPublisherByStateCode(this.stateCode)
        .subscribe((publisher: any) => {
          this.publisher = publisher;
        }, (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
        });
    }
  }
}
