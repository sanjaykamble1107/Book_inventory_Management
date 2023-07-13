import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  CanActivate,
  Router,
} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {

  constructor(public authService: AuthService, public router: Router) { }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot)
    : Observable<boolean> | Promise<boolean> | boolean {

    const roleNumber: any = this.authService.getRoleNumber();

    console.log(roleNumber)

    // const requiredRoleNumber = next.data['roleNumber'] as number;
    let requiredRoleNumber= next.data['roleNumber'];
    
    console.log(requiredRoleNumber);
     const b:boolean  =requiredRoleNumber.includes(roleNumber);
     console.log("b---"+b);
    //if (roleNumber == requiredRoleNumber || roleNumber == 4)  

    if ((roleNumber!=null &&  requiredRoleNumber.includes(roleNumber)) || roleNumber == "Admin") {

      return true;

    } else {
      alert('you are not authorized')
      // this.router.navigate(['/login']);// Redirect to default route or unauthorized page
       this.authService.navigateToDashboard(roleNumber);
      return false;
    }
  }
}


