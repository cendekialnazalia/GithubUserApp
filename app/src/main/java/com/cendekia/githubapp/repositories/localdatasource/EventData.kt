package com.cendekia.githubapp.repositories.localdatasource

import com.cendekia.githubapp.R
import com.cendekia.githubapp.repositories.models.Event

object EventData {
    private val eventNames = arrayOf(
        "GitHub CTF - A Call to Hacktion!",
        "GitHub Satellite India 2021",
        "Google Cloud Next ’20 OnAir "
    )

    private val eventLocations = arrayOf(
        "San Francisco Bay Area, California",
        "Mumbai, India",
        "Mountain View, California, Amerika"
    )

    private val eventImages = intArrayOf(
        R.drawable.events_one,
        R.drawable.events_two,
        R.drawable.events_three
    )

    private val eventdetailHeadline = arrayOf(
        "GitHub Security Lab Capture the Flag : A call to hacktion",
        "GitHub Satellite India 2021 Workshops : A community connected by code",
        "Announcing Google Cloud Next ’20: OnAir, a multi-week"
    )

    private val eventdetailAuthor = arrayOf(
        "John Smith", "Github India", "Google"
    )

    private val eventdetailDate = arrayOf(
        "10  Jan, 2021", "10  Feb, 2021", "10  Mar, 2021"
    )

    private val eventdetailContent = arrayOf(
        "Save the date! March 17 to 21, take your chance with the GitHub CTF “A Call to Hacktion!”\n" +
                "\n" +
                "What is a CTF? In software security, a Capture the Flag (CTF) is a contest where participants conduct real-world attacks on a given target to find and exploit a security vulnerability. When the exploit is successful, they can get their hands on a “flag.”\n" +
                "\n" +
                "Are you an action hero? If you use and like GitHub Actions, this CTF is the opportunity to challenge your knowledge and your workflow writing skills. It’s also the opportunity to learn about the nitty gritty of workflow security considerations! This contest is a solo activity, and you only need your GitHub Actions skills to participate.\n" +
                "\n" +
                "Follow @ghsecuritylab on Twitter, or keep your eyes on our CTF page for additional details.\n" +
                "\n" +
                "Mark the date on your calendar: March 17!",

        "Join us virtually for two days dedicated to celebrating India’s developer community. Expect announcements from GitHub leaders, hands-on workshops, and inspiring performances by artists who code.\n" +
                "\n" +
                "Celebrating the Developer Community in India: Past, Present and Future\n" +
                " Join us as we celebrate the innovation and creativity of the thriving developer community in India. Erica will share insight into the incredible progress of the past year, and look ahead to exciting announcements and programs to empower the developers of tomorrow.\n" +
                "\n" +
                "Day 2 featured session\n" +
                "Shanku Niyogi | SVP Product, GitHub\n" +
                "\n" +
                "This demo-packed session will show you how to build for the global market using the latest tools from GitHub. Discover how to take a product from creation to coding to deployment, and how GitHub builds its own software to help teams across the world work and collaborate.",

        "Since my last update, we’ve been working hard to evolve Next ’20 into an immersive and impactful digital experience for everyone. Today I’m excited to introduce Google Cloud Next ’20: OnAir—a free, nine-week, in-depth digital event series starting July 14.\n" +
                "\n" +
                "Next OnAir will offer fresh content each week through September 8, with over 200 sessions ranging from compelling keynotes from industry luminaries to advanced learning opportunities with top Google developers. Learn how the world’s leading companies are solving their biggest challenges, and hear firsthand how their digital transformation journeys have prepared them for this moment. Next OnAir will include interactive developer and learning programs such as Study Jams, Cloud Hero, and free one-month access to our curated learning paths on Qwiklabs and Pluralsight to help you prepare for certification. Connect with experts, get inspired, and expand your knowledge. \n" +
                "\n" +
                "If you were previously registered for Next ’20, you don’t have to do a thing—you’re automatically registered for Google Cloud Next ’20: OnAir. If you haven’t yet registered, you can get started today, for free, on the Next OnAir website. By registering, you will be able to build a customized program and engage with Googlers, partners and industry peers throughout the series.\n" +
                "\n" +
                "Starting July 14, each week will feature a new topic, check out the line-up below:"
    )

    val listData: ArrayList<Event>
        get() {
            val list = arrayListOf<Event>()
            for (position in eventNames.indices) {
                val event = Event()
                event.name_events = eventNames[position]
                event.location_events = eventLocations[position]
                event.photo_events = eventImages[position]
                event.headline_detail_events = eventdetailHeadline[position]
                event.author_detail_events = eventdetailAuthor[position]
                event.date_detail_event = eventdetailDate[position]
                event.content_detail_event = eventdetailContent[position]
                list.add(event)
            }
            return list
        }

}