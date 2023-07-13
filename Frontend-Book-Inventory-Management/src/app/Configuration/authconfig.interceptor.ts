import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpRequest, HttpHandler } from "@angular/common/http";
import { AuthService } from "./auth.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private authService: AuthService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler) {
   
        const authToken = this.authService.getToken();
        console.log('token',authToken);

        if(authToken==null){
            return next.handle(req);
            console.log("in null authtoken check")
        }

        req = req.clone({
            setHeaders: {
                // Authorization: "Bearer " + authToken
                Authorization: `Bearer ${authToken}`,
               
            }
        
        });
        console.log("token present and header added")

        return next.handle(req);
    }
}