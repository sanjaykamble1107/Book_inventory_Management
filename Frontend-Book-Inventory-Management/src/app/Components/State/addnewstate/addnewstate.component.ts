import { Component, OnInit } from '@angular/core';
import { State } from 'src/app/Models/state';
import { StateService } from 'src/app/service/state.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-addnewstate',
  templateUrl: './addnewstate.component.html',
  styleUrls: ['./addnewstate.component.css']
})
export class AddnewstateComponent implements OnInit {
  state: State = new State();     //creating state object   
  constructor(private stateService: StateService, private router: Router) { }

  ngOnInit(): void {

    // this.goToStateList();
  }

  saveState() {
    console.log("save state called");
    this.stateService.createState(this.state).subscribe((res:any) => {
      console.log("state added");
      alert(res.responseMessage)
      this.goToStateList();

    },
      (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      });
  }

  // Redirecting to List of States
  goToStateList() {
    this.router.navigate(['/getallstate']);
  }

  onSubmit() {
    console.log(this.state);
    this.saveState();
  }
}
