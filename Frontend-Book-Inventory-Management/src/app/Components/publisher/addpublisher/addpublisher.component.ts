import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Publisher } from 'src/app/Models/publisher';
import { State } from 'src/app/Models/state';
import { PublisherService } from 'src/app/service/publisher.service';
import { StateService } from 'src/app/service/state.service';

@Component({
  selector: 'app-addpublisher',
  templateUrl: './addpublisher.component.html',
  styleUrls: ['./addpublisher.component.css']
})
export class AddpublisherComponent implements OnInit {

  @ViewChild("publisherForm") publisherForm!:NgForm;
  
  publisher: Publisher = new Publisher(); // Creating a new Publisher object
  stateList:State[]=[];

  constructor(private publisherService: PublisherService, private router: Router,private stateService:StateService) { }

  ngOnInit(): void {
   this.stateService.getStateList().subscribe(
    (res:any)=>{
      this.stateList=res;
    },(error:HttpErrorResponse)=>{
      let errorMessage=error.error.errorMessage;
      alert(errorMessage);
    }
   )
  }

  // Function to add a new publisher
  addPublisher() {
    console.log(this.publisher);
    this.publisher.publisherId=100000;
    this.publisherService.addPublisher(this.publisher).subscribe(
      (response:any) => {
        let msg:string=response.responseMessage.toString();
        // alert('Publisher added successfully');
        alert(msg);
        this.publisherForm.reset();
      
        
      },
      (error: HttpErrorResponse) => {
        const errorMessage:string = error.error.errorMessage;
        console.log(errorMessage);
        alert(errorMessage);
      }
    );
  }
}
