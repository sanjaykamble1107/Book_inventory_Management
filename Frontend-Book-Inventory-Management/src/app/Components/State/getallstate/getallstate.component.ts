import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StateService } from 'src/app/service/state.service';
import { State } from './state';

@Component({
  selector: 'app-getallstate',
  templateUrl: './getallstate.component.html',
  styleUrls: ['./getallstate.component.css']
})
export class GetallstateComponent implements OnInit {
  statelist: State[] = [];
  filteredStates: State[] = [];
  searchCode: string = '';
  state: State[] = [];
  oneState: State | undefined;
  searchStateCode: string = '';
  updatedStateNameInput: string = '';
  stateCodeToUpdate: string = '';

  constructor(private stateService: StateService, private router: Router) { }

  ngOnInit() {
    this.stateService.getStateList().subscribe((res: any) => {
      console.log("Getting list");
      this.statelist = [...res];
      this.state = this.statelist;
      console.log("statelist displayed");
    }, (error: HttpErrorResponse) => {
      const errorMessage = error.error.errorMessage;
      alert(errorMessage);
    });
  }
  searchByStateCode() {
    if (this.searchStateCode.trim() !== '') {
      this.state = this.statelist.filter((state: State) => {
        return state.stateCode.toLowerCase().includes(this.searchStateCode.toLowerCase());
      },);
    }
    else {
      this.filteredStates = [...this.statelist];
    }
  }
}


