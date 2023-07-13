import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { StateService } from 'src/app/service/state.service';
import { State } from '../getallstate/state';

@Component({
  selector: 'app-update-state',
  templateUrl: './update-state.component.html',
  styleUrls: ['./update-state.component.css']
})
export class UpdateStateComponent {
  state: State;

  constructor(private stateService: StateService,private router:Router) {
    this.state = new State();
  }

  updateStateName() {
    this.stateService.updateStateName(this.state.stateCode, this.state.stateName)
      .subscribe(response => {
        // Handle the response, e.g., show a success message
        alert('State name updated successfully');
        console.log('State name updated successfully');
        this.goToStateList();
        
      }, (error: HttpErrorResponse) => {
        const errorMessage = error.error.errorMessage;
        alert(errorMessage);
      });
  }

  goToStateList() {
    this.router.navigate(['/getallstate']);
  }
}
