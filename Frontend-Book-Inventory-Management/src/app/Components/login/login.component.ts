import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/Configuration/auth.service';
import { LoginRequest } from 'src/app/Configuration/Model/LoginRequest';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {


  signinForm: FormGroup;

  constructor(

    public fb: FormBuilder,

    public authService: AuthService,

    public router: Router

  ) {

    this.signinForm = this.fb.group({
      userName: [''],

      password: ['']

    });

  }

  ngOnInit(): void {
    let status=this.authService.isLoggedIn;
   if(status){
    const role:string | null=this.authService.getRoleNumber();
    if(role!=null )
    this.authService.navigateToDashboard(role);

    else
    this.router.navigate(['/home']);
   }
  }




  request: LoginRequest = new LoginRequest;

  loginUser() {



    this.request = this.signinForm.value;

    console.log(this.request);

    this.authService.signIn(this.request);

  }
}

