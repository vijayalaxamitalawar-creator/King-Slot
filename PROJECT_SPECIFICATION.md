# LuckySpin5Reel - Complete Project Specification

## 1. App Overview
- **App Name:** LuckySpin5Reel
- **Platform:** Android (Mobile)
- **Game Type:** Entertainment with Virtual Coins
- **Total Games:** 150 unique games
- **Real Money:** None (Virtual coins only)

## 2. Core Features

### A. Player System
- Mobile number based registration/login
- Unique Player ID (auto-generated)
- Player profile with coin balance
- Game history tracking
- Referral system with unique code

### B. Game System (150 Games)
- **Main Game:** 5-Reel LuckySpin5Reel
- **Other Games:** 149 different games with various themes
- **Game Types:**
  - Slot games (3-Reel, 4-Reel, 5-Reel)
  - Different themes (Ancient, Modern, Fantasy, etc.)
  - Different mechanics and symbol combinations

### C. Game Features
- **Wild Symbols** - Substitute for other symbols
- **Scatter Symbols** - Trigger Free Spins/Bonus
- **Free Spins** - Default 15 free spins on 4+ Scatters
- **Multipliers** - Increase win amount
- **Bonus Rounds** - Special bonus features
- **Smooth Animations** - Reels spin smoothly
- **Sound Effects** - Spin, win, bonus sounds

### D. Betting System
- **Bet Range:** 1 to 100 virtual coins
- **Auto-Deduct:** Coins deducted on spin
- **Win-Add:** Coins added on winning
- **Fair System:** Server-side RNG for fairness

### E. Admin Panel Features
- **Secure Login** - Mobile + Password authentication
- **Game Management:** Activate/Deactivate games
- **Game Settings:** Configure difficulty (Easy/Normal/Hard)
- **Payout Control:** Adjust payout percentages
- **Player Management:** Search by mobile, view details
- **Coin Management:** Add/Deduct player coins
- **Game History:** View player game logs
- **Referral Settings:** Configure referral rewards
- **Support Details:** Manage support contact
- **Reports/Stats:** View player statistics
- **Activity Logs:** Audit trail of all admin actions

### F. Referral System
- **Unique Code:** Every player gets referral code
- **Rewards:** Virtual coins for successful referrals
- **Tracking:** Referral history in player profile
- **Admin Control:** Configure reward amount

### G. Support System
- **Support Contact:** Admin updates support number
- **Player Access:** Contact shown in player app
- **Support Team:** Managed by admin

## 3. Database Schema (Firebase)

### Collections:

#### players
```
- uid: string (Firebase Auth UID)
- mobileNumber: string (unique)
- playerId: string (unique, auto-generated)
- password: string (hashed)
- coinBalance: number
- totalCoinsWon: number
- totalCoinsLost: number
- referralCode: string (unique)
- referredBy: string (referrer's playerId)
- createdAt: timestamp
- lastLogin: timestamp
- active: boolean
```

#### games
```
- gameId: number (1-150)
- gameName: string
- gameDescription: string
- theme: string
- reels: number (3, 4, or 5)
- rows: number
- difficulty: string (EASY, NORMAL, HARD)
- payoutPercentage: number (0-100)
- minBet: number
- maxBet: number
- symbols: array
- hasWild: boolean
- hasScatter: boolean
- freeSpinsOnScatter: number
- multiplierEnabled: boolean
- bonusEnabled: boolean
- active: boolean
- createdAt: timestamp
```

#### gameResults
```
- resultId: string
- playerId: string
- gameId: number
- bet: number
- result: string (LOSS, WIN, BIGWIN, MEGAWIN)
- winAmount: number
- symbols: array (final symbols shown)
- timestamp: timestamp
```

#### referrals
```
- referralId: string
- referrerId: string
- referredPlayerId: string
- rewardAmount: number
- status: string (pending, completed)
- createdAt: timestamp
```

#### adminSettings
```
- whatsappNumber: string
- supportEmail: string
- referralRewardAmount: number
- minCoinWithdraw: number (not applicable - virtual only)
- maintenanceMode: boolean
- lastUpdated: timestamp
```

#### adminAuditLogs
```
- logId: string
- adminId: string
- action: string
- details: string
- timestamp: timestamp
```

## 4. Security Requirements

### Client-Side
- No sensitive API keys hardcoded
- No game logic exposed in APK
- Secure token storage
- SSL/TLS for all communications

### Server-Side
- All game results calculated server-side
- Input validation on all endpoints
- Rate limiting to prevent abuse
- Admin authentication required
- Audit logging for admin actions
- Secure password hashing (bcrypt)
- JWT tokens for sessions

## 5. UI/UX Design

### Main Menu
- Logo and branding
- Player ID and coin balance
- Game categories
- Quick access to games
- Bonus/Reward section
- Referral information
- Profile button
- Support button
- Settings button

### Game Screen
- 5 reels display
- Spin button (prominent)
- Bet selector (1-100)
- Coin balance display
- Current bet display
- Win display
- Auto-spin option
- Quick-spin option
- Sound toggle

### Profile Screen
- Player ID
- Coin balance
- Total wins/losses
- Referral code
- Referral count
- Game history
- Account settings

## 6. Sounds & Animations

### Sounds
- Background music (optional toggle)
- Spin sound
- Win sound
- Big win sound
- Mega win sound
- Scatter trigger sound
- Free spin sound
- Button click sound

### Animations
- Smooth reel spinning
- Win highlight effect
- Coin flip animation
- Symbol reveal effect
- Scatter bounce effect
- Free spin popup
- Bonus round transition

## 7. Admin Panel Features Detail

### Dashboard
- Total players count
- Active players today
- Total coins in circulation
- Top games by plays
- Recent transactions

### Player Management
- Search by mobile number
- View player details
- View player game history
- Add coins (admin function)
- Deduct coins (admin function)
- Ban/Unban player
- View referral network

### Game Management
- List all 150 games
- Enable/Disable games
- Edit game settings
- Configure difficulty
- Set payout percentage
- Manage bonus features

### Settings
- Admin profile
- Change password
- Update support contact
- Configure referral rewards
- System maintenance
- Backup/Export data

## 8. Performance Requirements

### Android App
- Min SDK: 21 (Android 5.0)
- Target SDK: 33+
- Smooth 60 FPS animations
- Quick game load (<2 seconds)
- Low memory footprint
- Battery efficient

## 9. Testing Requirements
- Unit tests for game logic
- Integration tests for API calls
- Performance testing on low-end devices
- Security testing (penetration test)
- Compatibility testing (multiple Android versions)

## 10. Play Store Requirements
- Compliant with Google Play policies
- Clear disclaimer: Virtual coins only
- No real money gambling references
- Privacy policy
- Terms of service
- Age rating (12+ or higher)
