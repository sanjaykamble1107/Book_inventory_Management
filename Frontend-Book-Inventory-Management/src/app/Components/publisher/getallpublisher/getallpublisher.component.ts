import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/Configuration/auth.service';
import { PublisherService } from 'src/app/service/publisher.service';

@Component({
  selector: 'app-getallpublisher',
  templateUrl: './getallpublisher.component.html',
  styleUrls: ['./getallpublisher.component.css']
})
export class GetallpublisherComponent implements OnInit {
  publishers: any[] = []; // Array to store publishers
  showForm: boolean = false; // Flag to show/hide update form
  selectedPublisher: any; // Selected publisher for update

  userRole:any='';


  constructor(private publisherService: PublisherService,private authService:AuthService) { }

  ngOnInit(): void {
    // Fetch all publishers on component initialization
    this.publisherService.getAllPublisher().subscribe(
      (res: any) => {
        this.publishers = [...res];
        console.log(this.publishers);
      },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      }
    );


     this.userRole= this.authService.getRoleNumber();

  }

  // Show update form for the selected publisher
  showUpdateForm(publisherId: number) {
    this.selectedPublisher = this.publishers.find(
      (publisher) => publisher.publisherId === publisherId
    );
    this.showForm = true;
  }

  // Update the selected publisher
  updatePublisher() {
    this.publisherService
      .updatePublisher(
        this.selectedPublisher.publisherId,
        this.selectedPublisher.name,
        this.selectedPublisher.city,
        this.selectedPublisher.stateCode
      )
      .subscribe(
        () => {
          // Update successful
          console.log('Publisher updated successfully');
          // Reset form and hide update form
          this.selectedPublisher = null;
          this.showForm = false;
        },
        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage;
          alert(errorMessage);
        }
      );
  }
}
