# King Slot - Complete Project Structure

## Android App (Kotlin)

### Main Components
```
android-app/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/kingslot/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── LoginActivity.kt
│   │   │   │   ├── GameActivity.kt
│   │   │   │   ├── ProfileActivity.kt
│   │   │   │   ├── WithdrawalActivity.kt
│   │   │   │   ├── models/
│   │   │   │   │   ├── Player.kt
│   │   │   │   │   ├── Game.kt
│   │   │   │   │   ├── Transaction.kt
│   │   │   │   │   └── WithdrawalRequest.kt
│   │   │   │   ├── viewmodels/
│   │   │   │   │   ├── GameViewModel.kt
│   │   │   │   │   ├── PlayerViewModel.kt
│   │   │   │   │   └── AdminViewModel.kt
│   │   │   │   ├── utils/
│   │   │   │   │   ├── FirebaseManager.kt
│   │   │   │   │   ├── SoundManager.kt
│   │   │   │   │   ├── AnimationUtils.kt
│   │   │   │   │   └── Constants.kt
│   │   │   │   ├── adapters/
│   │   │   │   │   ├── GameListAdapter.kt
│   │   │   │   │   └── TransactionAdapter.kt
│   │   │   │   └── games/
│   │   │   │       ├── SlotGame.kt (Base class)
│   │   │   │       ├── Game1.kt to Game150.kt
│   │   │   │       └── GameFactory.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── activity_login.xml
│   │   │   │   │   ├── activity_game.xml
│   │   │   │   │   ├── activity_profile.xml
│   │   │   │   │   ├── activity_withdrawal.xml
│   │   │   │   │   └── game_item.xml
│   │   │   │   ├── drawable/
│   │   │   │   │   ├── symbols/
│   │   │   │   │   ├── buttons/
│   │   │   │   │   └── backgrounds/
│   │   │   │   ├── raw/
│   │   │   │   │   ├── background_music.mp3
│   │   │   │   │   ├── spin_sound.mp3
│   │   │   │   │   ├── win_sound.mp3
│   │   │   │   │   ├── megawin_sound.mp3
│   │   │   │   │   └── free_game_sound.mp3
│   │   │   │   └── values/
│   │   │   │       ├── strings.xml
│   │   │   │       ├── colors.xml
│   │   │   │       └── dimens.xml
│   │   │   └── AndroidManifest.xml
│   │   ├── test/
│   │   └── androidTest/
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
└── settings.gradle
```

## Admin Panel (Web)

```
admin-panel/
���── index.html
├── login.html
├── dashboard.html
├── css/
│   ├── style.css
│   ├── dashboard.css
│   └── responsive.css
├── js/
│   ├── firebase-config.js
│   ├── admin-auth.js
│   ├── game-management.js
│   ├── player-management.js
│   ├── support-settings.js
│   └── dashboard.js
├── images/
│   └── icons/
└── README.md
```

## Features Breakdown

### Android App Features
1. **Player Authentication**
   - Mobile number & password login
   - Auto-login with saved credentials
   - Unique Player ID generation

2. **Game System**
   - 150 different games
   - Different game mechanics per game
   - 3 difficulty levels (Easy, Medium, Hard)
   - Realistic 3D symbols

3. **Sound System**
   - Background music
   - Spin sound effect
   - Win sound
   - Megawin special sound
   - Free game popup sound

4. **Player Profile**
   - Player information display
   - Current balance
   - Transaction history
   - Support WhatsApp number
   - Withdrawal option

5. **Withdrawal System**
   - Withdrawal request creation
   - Auto-attach player ID & mobile
   - Send to admin for approval

### Admin Panel Features
1. **Authentication**
   - Mobile number + password login
   - Secure session management

2. **Game Management**
   - Add new games
   - Edit game parameters
   - Delete games
   - Set game difficulty

3. **Player Management**
   - Search player by mobile number
   - View player profile
   - Add/Deduct coins
   - View transaction history
   - Approve/Reject withdrawals

4. **Support Settings**
   - Add WhatsApp support number
   - Edit support number
   - View all withdrawal requests
   - Player communication log

## Database Schema (Firebase)

### Collections
1. **players**
   - uid (auto-generated)
   - mobileNumber
   - password (hashed)
   - playerId (unique)
   - balance
   - totalWinnings
   - totalLosses
   - createdAt
   - lastLogin

2. **games**
   - gameId (1-150)
   - gameName
   - difficulty
   - payoutPercentage
   - minBet
   - maxBet
   - symbols
   - active

3. **transactions**
   - transactionId
   - playerId
   - gameId
   - amount
   - type (win/loss/bet)
   - timestamp

4. **withdrawals**
   - withdrawalId
   - playerId
   - mobileNumber
   - amount
   - status (pending/approved/rejected)
   - requestedAt
   - processedAt

5. **admin_settings**
   - whatsappNumber
   - supportTeamNumber
   - gamesCount
   - lastUpdated

## API Endpoints (Firebase Functions)
- POST /api/player/login
- POST /api/player/register
- GET /api/player/:id
- PUT /api/player/:id/coins
- GET /api/games
- POST /api/games
- DELETE /api/games/:id
- POST /api/withdrawal/request
- GET /api/admin/withdrawals
