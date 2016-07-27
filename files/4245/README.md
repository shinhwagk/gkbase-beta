### 1. 定义一个CanDeactivate(守卫)
```typescript
import { CanActivate }    from '@angular/router';

export class AuthGuard implements CanActivate {
  canActivate() {
    console.log('AuthGuard#canActivate called');
    return true;
  }
  /*canActivate() {
    if (false) {
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }*/
}
```
> 守卫需要返回true 和 false

### 2. 在路由中使用上面定义的守卫
```typescript
{
  path: 'admin',
  component: CrisisAdminComponent,
  canActivate: [AuthGuard]
},
```

### 3. 把守卫加入到路由器依赖appRouterProviders中
```typescript
export const appRouterProviders = [
  provideRouter(routes),
  [AuthGuard]
]; 
```
