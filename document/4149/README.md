```typescript
import { Component } from '@angular/core';
@Component({
  selector: 'my-app',
  template: '<h1>{{ name }} {{ 1+1+fun() }}</h1>' //3
})
export class AppComponent {
  name ="Jay"

  fun(){
    return 1
  }
}
```

####例子
```typescript
<img src="{{heroImageUrl}}" style="height:30px">
<p>The sum of 1 + 1 is not {{1 + 1 + getVal()}}</p>
```
