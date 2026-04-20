package com.ramaphosa.markethub.ui.screens.about

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ramaphosa.markethub.ui.screens.navigation.ROUT_HOME
import kotlinx.coroutines.delay

// ─── Color Palette ────────────────────────────────────────────────────────────
private val BrandGreen    = Color(0xFF1B6B3A)
private val AccentGreen   = Color(0xFF2E9C55)
private val LightGreen    = Color(0xFFD6F0DF)
private val SurfaceWhite  = Color(0xFFF8FBF9)
private val TextPrimary   = Color(0xFF0E2118)
private val TextSecondary = Color(0xFF5A7566)
private val CardBg        = Color.White
private val DividerColor  = Color(0xFFE3EDE7)
private val GoldStar      = Color(0xFFFFC107)

// ─── Data Classes ─────────────────────────────────────────────────────────────
private data class TeamMember(val initials: String, val name: String, val role: String, val color: Color)
private data class Milestone(val year: String, val title: String, val desc: String)
private data class ReviewItem(val author: String, val rating: Int, val comment: String, val date: String)
private data class FaqItem(val question: String, val answer: String)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {

    var selectedIndex  by remember { mutableStateOf(0) }
    var contentVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { delay(120); contentVisible = true }

    Scaffold(
        containerColor = SurfaceWhite,

        topBar = {
            TopAppBar(
                title = {
                    Text("About Us", fontWeight = FontWeight.Bold,
                        fontSize = 20.sp, letterSpacing = 0.3.sp)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Box(
                            Modifier.size(36.dp).clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.18f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.ArrowBack, "Back",
                                tint = Color.White, modifier = Modifier.size(20.dp))
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Share, "Share",
                            tint = Color.White, modifier = Modifier.size(20.dp))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BrandGreen,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        },

        bottomBar = {
            NavigationBar(
                containerColor = Color.White, tonalElevation = 0.dp,
                modifier = Modifier.shadow(12.dp, spotColor = BrandGreen.copy(alpha = 0.12f))
            ) {
                listOf(
                    Triple("Home",      Icons.Default.Home,    0),
                    Triple("Favorites", Icons.Filled.Favorite, 1),
                    Triple("Profile",   Icons.Default.Person,  2),
                ).forEach { (label, icon, idx) ->
                    NavigationBarItem(
                        icon  = { Icon(icon, label, Modifier.size(22.dp)) },
                        label = {
                            Text(label, fontSize = 11.sp,
                                fontWeight = if (selectedIndex == idx) FontWeight.SemiBold else FontWeight.Normal)
                        },
                        selected = selectedIndex == idx,
                        onClick  = { selectedIndex = idx; if (idx == 0) navController.navigate(ROUT_HOME) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor   = BrandGreen,
                            selectedTextColor   = BrandGreen,
                            indicatorColor      = LightGreen,
                            unselectedIconColor = TextSecondary,
                            unselectedTextColor = TextSecondary
                        )
                    )
                }
            }
        }
    ) { pv ->

        Column(
            Modifier
                .padding(pv)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            // ── 1. Hero ────────────────────────────────────────────────────────
            Anim(contentVisible, 0) {
                Box(
                    Modifier.fillMaxWidth().height(230.dp)
                        .background(Brush.verticalGradient(listOf(BrandGreen, AccentGreen))),
                    contentAlignment = Alignment.Center
                ) {
                    Box(Modifier.size(200.dp).offset(110.dp, (-40).dp).clip(CircleShape)
                        .background(Color.White.copy(0.05f)))
                    Box(Modifier.size(130.dp).offset((-90).dp, 55.dp).clip(CircleShape)
                        .background(Color.White.copy(0.05f)))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            Modifier.size(76.dp).clip(CircleShape)
                                .background(Color.White.copy(0.16f))
                                .border(2.dp, Color.White.copy(0.45f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) { Text("M", fontSize = 34.sp, fontWeight = FontWeight.ExtraBold, color = Color.White) }
                        Spacer(Modifier.height(12.dp))
                        Text("MarketHub", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold,
                            color = Color.White, letterSpacing = 0.5.sp)
                        Spacer(Modifier.height(4.dp))
                        Text("Africa's trusted marketplace", fontSize = 13.sp, color = Color.White.copy(0.80f))
                    }
                }
            }

            // ── 2. Stats Row ───────────────────────────────────────────────────
            Anim(contentVisible, 1) {
                Row(
                    Modifier.fillMaxWidth().padding(horizontal = 20.dp).offset(y = (-22).dp)
                        .shadow(8.dp, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp)).background(CardBg).padding(vertical = 18.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem("50K+", "Products")
                    VDiv()
                    StatItem("12K+", "Sellers")
                    VDiv()
                    StatItem("4.8",  "Rating", Icons.Default.Star)
                    VDiv()
                    StatItem("98%",  "Satisfied")
                }
            }

            Spacer(Modifier.height(4.dp))

            // ── 3. Mission ─────────────────────────────────────────────────────
            Anim(contentVisible, 2) {
                SCard(Modifier.padding(horizontal = 20.dp)) {
                    SLabel("OUR MISSION")
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "MarketHub connects buyers and sellers across Africa's growing digital economy. " +
                                "We make commerce accessible, transparent, and empowering — whether you're " +
                                "shopping from home or scaling your small business to new markets.",
                        fontSize = 14.sp, color = TextSecondary, lineHeight = 22.sp
                    )
                    Spacer(Modifier.height(14.dp))
                    Box(Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(4.dp))
                        .background(Brush.horizontalGradient(listOf(BrandGreen, AccentGreen))))
                }
            }

            Gap()

            // ── 4. Our Vision ──────────────────────────────────────────────────
            Anim(contentVisible, 3) {
                SCard(Modifier.padding(horizontal = 20.dp)) {
                    SLabel("OUR VISION")
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "By 2030, we aim to be the most trusted digital marketplace on the African continent — " +
                                "enabling 1 million small businesses to thrive, and making quality goods accessible " +
                                "to every household regardless of location or income level.",
                        fontSize = 14.sp, color = TextSecondary, lineHeight = 22.sp
                    )
                }
            }

            Gap()

            // ── 5. What We Offer ───────────────────────────────────────────────
            Anim(contentVisible, 4) {
                SCard(Modifier.padding(horizontal = 20.dp)) {
                    SLabel("WHAT WE OFFER")
                    Spacer(Modifier.height(12.dp))
                    listOf(
                        Icons.Outlined.Person   to "Verified sellers with full buyer protection",
                        Icons.Outlined.LocationOn     to "Localised delivery & pickup options",
                        Icons.Outlined.Home      to "Available across 10+ African countries",
                        Icons.Outlined.FavoriteBorder to "Personalised product recommendations",
                        Icons.Outlined.ShoppingCart   to "Seamless checkout with multiple payment options",
                        Icons.Outlined.Notifications  to "Real-time order tracking & push notifications",
                    ).forEachIndexed { i, (icon, text) ->
                        FeatRow(icon, text)
                        if (i < 5) Divider(color = DividerColor,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                }
            }

            Gap()

            // ── 6. Timeline ────────────────────────────────────────────────────
            Anim(contentVisible, 5) {
                SCard(Modifier.padding(horizontal = 20.dp)) {
                    SLabel("OUR STORY")
                    Spacer(Modifier.height(14.dp))
                    val milestones = listOf(
                        Milestone("2019", "Founded",            "Started as a small Nairobi-based platform with 20 sellers."),
                        Milestone("2020", "First 1 000 Users",  "Grew rapidly during the pandemic, helping local businesses go digital."),
                        Milestone("2021", "Pan-African Expansion", "Launched in Uganda, Tanzania, Ghana, Nigeria & Rwanda."),
                        Milestone("2023", "Mobile App Launch",  "Android & iOS apps hit 100 K downloads in 3 months."),
                        Milestone("2025", "Today",              "50 000+ products, 12 000+ verified sellers, 10+ countries."),
                    )
                    milestones.forEachIndexed { i, m -> TimeRow(m, i == milestones.lastIndex) }
                }
            }

            Gap()

            // ── 7. Team ────────────────────────────────────────────────────────
            Anim(contentVisible, 6) {
                SCard(Modifier.padding(horizontal = 20.dp)) {
                    SLabel("MEET THE TEAM")
                    Spacer(Modifier.height(14.dp))
                    val team = listOf(
                        TeamMember("CR", "Cyril Ramaphosa",  "Founder & CEO",          Color(0xFF1B6B3A)),
                        TeamMember("AM", "Amara Moyo",       "Head of Engineering",    Color(0xFF0277BD)),
                        TeamMember("FN", "Fatima Nkosi",     "Chief Product Officer",  Color(0xFF6A1B9A)),
                        TeamMember("KO", "Kwame Osei",       "Director of Growth",     Color(0xFFE65100)),
                        TeamMember("LM", "Leila Mensah",     "Head of Design",         Color(0xFF00695C)),
                    )
                    team.forEachIndexed { i, m ->
                        TeamRow(m)
                        if (i < team.lastIndex) Divider(color = DividerColor,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                }
            }

            Gap()

            // ── 8. Reviews ─────────────────────────────────────────────────────
            Anim(contentVisible, 7) {
                SCard(Modifier.padding(horizontal = 20.dp)) {
                    SLabel("CUSTOMER REVIEWS")
                    Spacer(Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("4.8", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                        Spacer(Modifier.width(14.dp))
                        Column {
                            Row { repeat(5) { Icon(Icons.Default.Star, null, Modifier.size(16.dp), tint = GoldStar) } }
                            Text("Based on 8 400+ ratings", fontSize = 12.sp, color = TextSecondary)
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    listOf(5 to 0.72f, 4 to 0.18f, 3 to 0.06f, 2 to 0.02f, 1 to 0.02f).forEach { (s, f) ->
                        RatingBar(s, f); Spacer(Modifier.height(4.dp))
                    }
                    Spacer(Modifier.height(14.dp))
                    Divider(color = DividerColor)
                    Spacer(Modifier.height(14.dp))
                    listOf(
                        ReviewItem("Grace K.",  5, "Super fast delivery and exactly what I ordered. Will shop again!", "Mar 2025"),
                        ReviewItem("Emeka T.", 5, "Best marketplace in West Africa. Customer support is excellent.",   "Feb 2025"),
                        ReviewItem("Amina S.", 4, "Great variety of products. Delivery tracking could be a bit faster.", "Jan 2025"),
                    ).forEachIndexed { i, r -> RevCard(r); if (i < 2) Spacer(Modifier.height(12.dp)) }
                }
            }

            Gap()

            // ── 9. Trust & Security ────────────────────────────────────────────
            Anim(contentVisible, 8) {
                SCard(Modifier.padding(horizontal = 20.dp)) {
                    SLabel("TRUST & SECURITY")
                    Spacer(Modifier.height(12.dp))
                    listOf(
                        Icons.Outlined.Lock         to "End-to-end encrypted payments",
                        Icons.Default.CheckCircle   to "30-day money-back guarantee",
                        Icons.Outlined.Person to "All sellers are KYC-verified",
                        Icons.Outlined.ThumbUp      to "Secure buyer protection on every order",
                    ).forEachIndexed { i, (icon, text) ->
                        TrustRow(icon, text)
                        if (i < 3) Divider(color = DividerColor,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                }
            }

            Gap()


            // ── 11. FAQs ───────────────────────────────────────────────────────
            Anim(contentVisible, 10) {
                SCard(Modifier.padding(horizontal = 20.dp)) {
                    SLabel("FREQUENTLY ASKED QUESTIONS")
                    Spacer(Modifier.height(12.dp))
                    listOf(
                        FaqItem("How do I become a seller?",
                            "Register a seller account, complete KYC verification, and list your first product — all in minutes."),
                        FaqItem("What payment methods are accepted?",
                            "We support M-Pesa, Airtel Money, bank cards (Visa/Mastercard), and direct bank transfer."),
                        FaqItem("How does delivery work?",
                            "We partner with local couriers in each country. Delivery takes 1–5 business days by region."),
                        FaqItem("What if I receive a wrong item?",
                            "Open a dispute within 7 days. Our team resolves most cases in 48 hours with full buyer protection."),
                        FaqItem("Is there a mobile app?",
                            "Yes! Download MarketHub on Android (Google Play) or iOS (App Store) for the best experience."),
                    ).forEachIndexed { i, faq ->
                        FaqRow(faq)
                        if (i < 4) Divider(color = DividerColor,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                }
            }

            Gap()

            // ── 12. Contact ────────────────────────────────────────────────────
            Anim(contentVisible, 11) {
                SCard(Modifier.padding(horizontal = 20.dp)) {
                    SLabel("GET IN TOUCH")
                    Spacer(Modifier.height(12.dp))
                    ContactRow(Icons.Default.Email,       "support@markethub.com")
                    Divider(color = DividerColor, modifier = Modifier.padding(vertical = 10.dp))
                    ContactRow(Icons.Default.Phone,       "+254 700 123 456")
                    Divider(color = DividerColor, modifier = Modifier.padding(vertical = 10.dp))
                    ContactRow(Icons.Outlined.LocationOn, "Nairobi, Kenya — Serving all of Africa")
                    Divider(color = DividerColor, modifier = Modifier.padding(vertical = 10.dp))
                    ContactRow(Icons.Outlined.Home,   "www.markethub.com")
                }
            }

            Gap()

            // ── 13. Legal ──────────────────────────────────────────────────────
            Anim(contentVisible, 12) {
                SCard(Modifier.padding(horizontal = 20.dp)) {
                    SLabel("LEGAL")
                    Spacer(Modifier.height(12.dp))
                    listOf(
                        Icons.Outlined.Info         to "Terms of Service",
                        Icons.Outlined.Lock         to "Privacy Policy",
                        Icons.Outlined.Build        to "Cookie Policy",
                        Icons.Outlined.AccountCircle to "Community Guidelines",
                    ).forEachIndexed { i, (icon, label) ->
                        LegalRow(icon, label)
                        if (i < 3) Divider(color = DividerColor,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // ── 14. Footer ─────────────────────────────────────────────────────
            Anim(contentVisible, 13) {
                Column(
                    Modifier.fillMaxWidth()
                        .background(Brush.verticalGradient(listOf(BrandGreen.copy(0.08f), LightGreen)))
                        .padding(vertical = 28.dp, horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(Modifier.size(48.dp).clip(CircleShape).background(BrandGreen),
                        contentAlignment = Alignment.Center) {
                        Text("M", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                    }
                    Spacer(Modifier.height(10.dp))
                    Text("MarketHub", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Spacer(Modifier.height(4.dp))
                    Text("Empowering African commerce, one sale at a time.",
                        fontSize = 12.sp, color = TextSecondary, textAlign = TextAlign.Center)
                    Spacer(Modifier.height(16.dp))
                    Divider(color = DividerColor)
                    Spacer(Modifier.height(12.dp))
                    Text("Version 1.0.0  •  © 2025 MarketHub. All rights reserved.",
                        fontSize = 11.sp, color = TextSecondary.copy(0.55f), textAlign = TextAlign.Center)
                }
            }
        }
    }
}


// ─── Animation wrapper ────────────────────────────────────────────────────────

@Composable
private fun Anim(visible: Boolean, idx: Int, content: @Composable () -> Unit) {
    val d = (idx * 80).coerceAtMost(600)
    AnimatedVisibility(visible, enter = fadeIn(tween(500, d)) + slideInVertically(tween(450, d)) { 36 }) { content() }
}

@Composable private fun Gap() = Spacer(Modifier.height(16.dp))


// ─── Cards & Labels ───────────────────────────────────────────────────────────

@Composable
private fun SCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Card(modifier.fillMaxWidth(), RoundedCornerShape(16.dp),
        CardDefaults.cardColors(CardBg), CardDefaults.cardElevation(2.dp)) {
        Column(Modifier.padding(20.dp)) { content() }
    }
}

@Composable
private fun SLabel(text: String) =
    Text(text, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BrandGreen, letterSpacing = 1.2.sp)


// ─── Atomic components ────────────────────────────────────────────────────────

@Composable
private fun StatItem(value: String, label: String, icon: ImageVector? = null) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) { Icon(icon, null, Modifier.size(15.dp), tint = GoldStar); Spacer(Modifier.width(2.dp)) }
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, color = TextPrimary)
        }
        Text(label, fontSize = 11.sp, color = TextSecondary)
    }
}

@Composable private fun VDiv() = Box(Modifier.height(34.dp).width(1.dp).background(DividerColor))

@Composable
private fun FeatRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconChip(icon, LightGreen, BrandGreen)
        Spacer(Modifier.width(14.dp))
        Text(text, fontSize = 14.sp, color = TextSecondary, lineHeight = 20.sp, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun TimeRow(m: Milestone, isLast: Boolean) {
    Row {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(52.dp)) {
            Box(Modifier.size(36.dp).clip(CircleShape).background(LightGreen), Alignment.Center) {
                Text(m.year.takeLast(2), fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BrandGreen)
            }
            if (!isLast) Box(Modifier.width(2.dp).height(40.dp).background(LightGreen))
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.padding(top = 6.dp)) {
            Text(m.title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            Spacer(Modifier.height(2.dp))
            Text(m.desc, fontSize = 13.sp, color = TextSecondary, lineHeight = 19.sp)
            if (!isLast) Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun TeamRow(m: TeamMember) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier.size(44.dp).clip(CircleShape)
                .background(m.color.copy(0.12f)).border(1.5.dp, m.color.copy(0.35f), CircleShape),
            Alignment.Center
        ) { Text(m.initials, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = m.color) }
        Spacer(Modifier.width(14.dp))
        Column {
            Text(m.name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            Text(m.role, fontSize = 12.sp, color = TextSecondary)
        }
    }
}

@Composable
private fun RatingBar(star: Int, fraction: Float) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("$star", fontSize = 12.sp, color = TextSecondary, modifier = Modifier.width(14.dp))
        Spacer(Modifier.width(4.dp))
        Icon(Icons.Default.Star, null, Modifier.size(12.dp), tint = GoldStar)
        Spacer(Modifier.width(8.dp))
        LinearProgressIndicator(fraction, Modifier.weight(1f).height(6.dp).clip(RoundedCornerShape(4.dp)),
            color = GoldStar, trackColor = DividerColor, strokeCap = StrokeCap.Round)
        Spacer(Modifier.width(8.dp))
        Text("${(fraction * 100).toInt()}%", fontSize = 11.sp, color = TextSecondary, modifier = Modifier.width(30.dp))
    }
}

@Composable
private fun RevCard(r: ReviewItem) {
    Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(SurfaceWhite).padding(14.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(34.dp).clip(CircleShape).background(LightGreen), Alignment.Center) {
                Text(r.author.first().toString(), fontSize = 14.sp, fontWeight = FontWeight.Bold, color = BrandGreen)
            }
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Text(r.author, fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                Row { repeat(5) { i -> Icon(Icons.Default.Star, null, Modifier.size(12.dp),
                    tint = if (i < r.rating) GoldStar else DividerColor) } }
            }
            Text(r.date, fontSize = 11.sp, color = TextSecondary)
        }
        Spacer(Modifier.height(8.dp))
        Text(r.comment, fontSize = 13.sp, color = TextSecondary, lineHeight = 19.sp)
    }
}

@Composable
private fun TrustRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconChip(icon, Color(0xFFE8F5E9), BrandGreen)
        Spacer(Modifier.width(14.dp))
        Text(text, fontSize = 14.sp, color = TextSecondary, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun AwardRow(title: String, year: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconChip(Icons.Outlined.Star, Color(0xFFFFF8E1), GoldStar)
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Text(title, fontSize = 13.sp, color = TextPrimary, lineHeight = 18.sp)
            Text(year, fontSize = 11.sp, color = TextSecondary)
        }
    }
}

@Composable
private fun FaqRow(faq: FaqItem) {
    Column {
        Text(faq.question, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
        Spacer(Modifier.height(4.dp))
        Text(faq.answer, fontSize = 13.sp, color = TextSecondary, lineHeight = 19.sp)
    }
}

@Composable
private fun ContactRow(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconChip(icon, LightGreen, BrandGreen)
        Spacer(Modifier.width(14.dp))
        Text(text, fontSize = 14.sp, color = TextPrimary, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun LegalRow(icon: ImageVector, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconChip(icon, Color(0xFFF1F1F1), TextSecondary)
        Spacer(Modifier.width(14.dp))
        Text(label, fontSize = 14.sp, color = TextPrimary, modifier = Modifier.weight(1f))
        Icon(Icons.Default.ArrowBack, null, Modifier.size(16.dp), tint = TextSecondary.copy(0.45f))
    }
}

// Shared icon chip used across rows
@Composable
private fun IconChip(icon: ImageVector, bg: Color, tint: Color) {
    Box(Modifier.size(38.dp).clip(RoundedCornerShape(10.dp)).background(bg), Alignment.Center) {
        Icon(icon, null, Modifier.size(19.dp), tint = tint)
    }
}


// ─── Preview ──────────────────────────────────────────────────────────────────

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(rememberNavController())
}